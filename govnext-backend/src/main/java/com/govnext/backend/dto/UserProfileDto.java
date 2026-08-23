package com.govnext.backend.dto;

import java.util.List;

public class UserProfileDto {

    private String name;
    private String email;
    private List<String> skills;
    private List<String> qualifications;

    public UserProfileDto() {}

    public UserProfileDto(String name, String email, List<String> skills, List<String> qualifications) {
        this.name = name;
        this.email = email;
        this.skills = skills;
        this.qualifications = qualifications;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public List<String> getQualifications() { return qualifications; }
    public void setQualifications(List<String> qualifications) { this.qualifications = qualifications; }
}