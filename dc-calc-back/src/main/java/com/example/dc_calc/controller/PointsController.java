package com.example.dc_calc.controller;

import com.example.dc_calc.service.ParametersServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/")
public class PointsController {

    @Autowired
    private ParametersServiceDb parametersService; // this gets parameters stored in database
    //private ParametersService parametersService; // this gets parameters that are hardcoded

    @CrossOrigin
    @GetMapping("/calc")
    public int calculatePoints(@RequestParam int event, @RequestParam double score) {
        System.out.println("event: " + event + ", score: " + score); //TODO needs better logging

        var params = parametersService.findById(event).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "unsupported event")
        );

        // Calculate score using IAAF Scoring formula
        score = score * params.getMultiplier();
        double m = params.isTimeBased() ? (params.getB() - score) : (score - params.getB());
        int points = (int) (Math.pow(m, params.getC()) * params.getA());

        System.out.println("points: " + points);
        return points;
    }

}
