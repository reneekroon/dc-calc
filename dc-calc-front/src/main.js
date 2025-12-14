import './style.css'

const events = [
  { id: 0, name: 'm100' },
  { id: 1, name: 'longjump' },
  { id: 2, name: 'shotput' },
  { id: 3, name: 'highjump' },
  { id: 4, name: 'm400' },
  { id: 5, name: 'hurdles' },
  { id: 6, name: 'discus' },
  { id: 7, name: 'polevault' },
  { id: 8, name: 'javelin' },
  { id: 9, name: 'm1500' }
];

let previousInputs = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
let previousResults = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

async function fetchResult(event, performance) {
  let request = `http://localhost:8080/api/v1/calc?event=${event.id}&score=${performance}`
  let response = await (await fetch(request)).text()
  return parseInt(response)
}

function convertTimeToSeconds(timeStr) {
  const parts = timeStr.split(':');
  if (parts.length === 1) return parseFloat(parts[0]);
  if (parts.length !== 2) return null;
  const minutes = parseFloat(parts[0]);
  const seconds = parseFloat(parts[1]);
  return minutes * 60 + seconds;
}

async function updateEventPoints(event) {
  const input = document.getElementById(event.name);
  const display = document.getElementById('points-' + event.name);

  let value = input.value;
  if (!value) {
    display.textContent = '0 pts';
    return 0;
  }

  let performance;
  if (event.name === 'm1500') {
    performance = convertTimeToSeconds(value);
    if (!performance) {
      display.textContent = 'Invalid format';
      return 0;
    }
  } else {
    performance = parseFloat(value);
  }

  // do not request again if same as previous time
  if (previousInputs[event.id] === performance) {
    return previousResults[event.id];
  }

  const points = await fetchResult(event, performance)
  display.textContent = points + ' pts';

  previousInputs[event.id] = performance;
  previousResults[event.id] = points;

  return points;
}

async function calculateAll() {
  let total = 0;
  for (let event of events) {
    total += await updateEventPoints(event);
  }

  document.getElementById('total-points').textContent = total;
}

function resetAll() {
  events.forEach(event => {
    document.getElementById(event.name).value = '';
    document.getElementById('points-' + event.name).textContent = '0 pts';
    previousInputs[event.id] = 0;
    previousResults[event.id] = 0;
  });

  document.getElementById('total-points').textContent = '0';
}

window.onload = () => {
  // Add real-time calculation on input
  // document.querySelectorAll('input').forEach(input => {
  //   input.addEventListener('input', calculateAll);
  // });
  document.getElementById('calc-btn').addEventListener('click', calculateAll);
  document.getElementById('reset-btn').addEventListener('click', resetAll);
}
