package com.govnext.backend.dto;

public class JobDto {
    private String title;
    private String department;
    private String description;
    private String location;
    private Double salary;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
}