package com.example.dc_calc.service;

import com.example.dc_calc.model.ScoringParameter;
import com.example.dc_calc.repository.ParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParametersServiceDb {

    @Autowired
    private ParametersRepository parametersRepository;

    public Optional<ScoringParameter> findById(int id) {
        if (id < 0) return Optional.empty();
        return parametersRepository.findById((long) id);
    }

}
