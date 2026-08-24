package com.govnext.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EligibilityRequest {
    private Integer age;
    private Double annualIncome;
    private String gender;
    private String state;
}