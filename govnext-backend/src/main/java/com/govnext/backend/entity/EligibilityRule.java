package com.govnext.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "eligibility_rules")
public class EligibilityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private String criteria;
    private String ruleValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    public EligibilityRule() {}

    public EligibilityRule(String ruleName, String criteria, String ruleValue, Job job) {
        this.ruleName = ruleName;
        this.criteria = criteria;
        this.ruleValue = ruleValue;
        this.job = job;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getCriteria() { return criteria; }
    public void setCriteria(String criteria) { this.criteria = criteria; }

    public String getRuleValue() { return ruleValue; }
    public void setRuleValue(String ruleValue) { this.ruleValue = ruleValue; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
}