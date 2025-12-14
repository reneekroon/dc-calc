package com.example.dc_calc.service;

import com.example.dc_calc.model.ScoringParameter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParametersService {

    private final ScoringParameter[] scoringParameters;

    public ParametersService() {
        // hardcoded scoring parameters
        scoringParameters = new ScoringParameter[10];
        scoringParameters[0] = new ScoringParameter(0L, true, 1, 25.4347, 18, 1.81); // 100m
        scoringParameters[1] = new ScoringParameter(1L, false, 100, 0.14354, 220, 1.4); // long_jump
        scoringParameters[2] = new ScoringParameter(2L, false, 1, 51.39, 1.5, 1.05); // short_put
        scoringParameters[3] = new ScoringParameter(3L, false, 100, 0.8465, 75, 1.42); // high_jump
        scoringParameters[4] = new ScoringParameter(4L, true, 1, 1.53775, 82, 1.81); // 400m
        scoringParameters[5] = new ScoringParameter(5L, true, 1, 5.74352, 28.5, 1.92); // 110m_hurdles
        scoringParameters[6] = new ScoringParameter(6L, false, 1, 12.91, 4, 1.1); // discus_throw
        scoringParameters[7] = new ScoringParameter(7L, false, 100, 0.2797, 100, 1.35); // pole_vault
        scoringParameters[8] = new ScoringParameter(8L, false, 1, 10.14, 7, 1.08); // javelin_throw
        scoringParameters[9] = new ScoringParameter(9L, true, 1, 0.03768, 480, 1.85); // 1500m
    }

    public Optional<ScoringParameter> findById(int id) {
        if (id < 0 || id >= scoringParameters.length) {
            return Optional.empty();
        }
        return Optional.of(scoringParameters[id]);
    }

}
