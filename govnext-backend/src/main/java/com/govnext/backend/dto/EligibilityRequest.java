package com.govnext.backend.dto;

public class EligibilityRequest {

    private Integer age;
    private Double annualIncome;
    private String gender;
    private String state;

    // Getters
    public Integer getAge() { return age; }
    public Double getAnnualIncome() { return annualIncome; }
    public String getGender() { return gender; }
    public String getState() { return state; }

    // Setters
    public void setAge(Integer age) { this.age = age; }
    public void setAnnualIncome(Double annualIncome) { this.annualIncome = annualIncome; }
    public void setGender(String gender) { this.gender = gender; }
    public void setState(String state) { this.state = state; }
}