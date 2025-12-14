package com.example.dc_calc.repository;

import com.example.dc_calc.model.ScoringParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametersRepository extends JpaRepository<ScoringParameter, Long> {

}
