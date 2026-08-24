package com.govnext.backend.dto;

public class EligibilityRequest {
    private Integer age;
    private Double annualIncome;
    private String gender;
    private String state;

    public EligibilityRequest() {}

    public EligibilityRequest(Integer age, Double annualIncome, String gender, String state) {
        this.age = age;
        this.annualIncome = annualIncome;
        this.gender = gender;
        this.state = state;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}