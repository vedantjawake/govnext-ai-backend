package com.govnext.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schemes")
public class Scheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String benefits;

    private Integer minAge;
    private Integer maxAge;
    private Double maxIncome;
    private String targetGender; // "ALL", "MALE", "FEMALE"
    private String requiredState; // "ALL" or specific state

    public Scheme() {}

    public Scheme(Long id, String name, String category, String description, String benefits, 
                  Integer minAge, Integer maxAge, Double maxIncome, String targetGender, String requiredState) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.benefits = benefits;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.maxIncome = maxIncome;
        this.targetGender = targetGender;
        this.requiredState = requiredState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Double getMaxIncome() {
        return maxIncome;
    }

    public void setMaxIncome(Double maxIncome) {
        this.maxIncome = maxIncome;
    }

    public String getTargetGender() {
        return targetGender;
    }

    public void setTargetGender(String targetGender) {
        this.targetGender = targetGender;
    }

    public String getRequiredState() {
        return requiredState;
    }

    public void setRequiredState(String requiredState) {
        this.requiredState = requiredState;
    }

    public static SchemeBuilder builder() {
        return new SchemeBuilder();
    }

    public static class SchemeBuilder {
        private Long id;
        private String name;
        private String category;
        private String description;
        private String benefits;
        private Integer minAge;
        private Integer maxAge;
        private Double maxIncome;
        private String targetGender;
        private String requiredState;

        SchemeBuilder() {}

        public SchemeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SchemeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SchemeBuilder category(String category) {
            this.category = category;
            return this;
        }

        public SchemeBuilder description(String description) {
            this.description = description;
            return this;
        }

        public SchemeBuilder benefits(String benefits) {
            this.benefits = benefits;
            return this;
        }

        public SchemeBuilder minAge(Integer minAge) {
            this.minAge = minAge;
            return this;
        }

        public SchemeBuilder maxAge(Integer maxAge) {
            this.maxAge = maxAge;
            return this;
        }

        public SchemeBuilder maxIncome(Double maxIncome) {
            this.maxIncome = maxIncome;
            return this;
        }

        public SchemeBuilder targetGender(String targetGender) {
            this.targetGender = targetGender;
            return this;
        }

        public SchemeBuilder requiredState(String requiredState) {
            this.requiredState = requiredState;
            return this;
        }

        public Scheme build() {
            return new Scheme(id, name, category, description, benefits, minAge, maxAge, maxIncome, targetGender, requiredState);
        }
    }
}