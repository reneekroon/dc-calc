package com.example.dc_calc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScoringParameter {

    @Id private Long id;
    private boolean isTimeBased;
    private double multiplier;
    private double a;
    private double b;
    private double c;

}
