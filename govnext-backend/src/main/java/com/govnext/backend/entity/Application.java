package com.govnext.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    private String status;
    private Double eligibilityScore;
    private LocalDateTime appliedAt;

    public Application() {
        this.appliedAt = LocalDateTime.now();
    }

    public Application(User user, Job job, String status, Double eligibilityScore) {
        this.user = user;
        this.job = job;
        this.status = status;
        this.eligibilityScore = eligibilityScore;
        this.appliedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getEligibilityScore() { return eligibilityScore; }
    public void setEligibilityScore(Double eligibilityScore) { this.eligibilityScore = eligibilityScore; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }
}