package com.example.dc_calc;

import com.example.dc_calc.controller.PointsController;
import com.example.dc_calc.service.ParametersService;
import com.example.dc_calc.service.ParametersServiceDb;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PointsController.class)
public class PointsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ParametersServiceDb parametersService;

    @BeforeEach
    public void setUp() {
        ParametersService replacement = new ParametersService();
        Mockito.when(parametersService.findById(Mockito.anyInt())).thenAnswer(call -> {
            var arg = call.getArgument(0);
            return replacement.findById((Integer) arg);
        });
    }

    @Test
    public void testPointsController() throws Exception {
        var testCases = new TestCase[]{
                new TestCase(0, 10, 1096),
                new TestCase(1, 7, 814),
                new TestCase(2, 15, 790),
                new TestCase(3, 2, 803),
                new TestCase(4, 45, 1060),
                new TestCase(5, 15, 850),
                new TestCase(6, 55, 975),
                new TestCase(7, 5.287, 1000),
                new TestCase(8, 75, 966),
                new TestCase(9, 240, 953),
        };

        for (var testCase : testCases) {
            mvc.perform(get("/api/v1/calc?event=" + testCase.event + "&score=" + testCase.score)).andExpectAll(
                    status().isOk(),
                    content().string(Integer.toString(testCase.expected))
            );
        }
    }

    @Test
    public void testPointsControllerErrors() throws Exception {
        var testCases = new TestCase[]{
                new TestCase(-1, 81, 0),
                new TestCase(10, 0.9, 0),
        };

        for (var testCase : testCases) {
            mvc.perform(get("/api/v1/calc?event=" + testCase.event + "&score=" + testCase.score)).andExpect(status().is4xxClientError());
        }
    }

    @AllArgsConstructor
    private final class TestCase {
        public int event;
        public double score;
        public int expected;
    }

}
