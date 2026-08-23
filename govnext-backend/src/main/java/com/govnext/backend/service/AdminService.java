package com.govnext.backend.service;

import com.govnext.backend.dto.EligibilityRuleDto;
import com.govnext.backend.entity.Application;
import com.govnext.backend.entity.EligibilityRule;
import com.govnext.backend.entity.Job;
import com.govnext.backend.exception.ResourceNotFoundException;
import com.govnext.backend.repository.ApplicationRepository;
import com.govnext.backend.repository.EligibilityRuleRepository;
import com.govnext.backend.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final EligibilityRuleRepository ruleRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final NotificationService notificationService;

    public AdminService(EligibilityRuleRepository ruleRepository, 
                        JobRepository jobRepository, 
                        ApplicationRepository applicationRepository,
                        NotificationService notificationService) {
        this.ruleRepository = ruleRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.notificationService = notificationService;
    }

    public EligibilityRule addRule(EligibilityRuleDto dto) {
        if (dto.getJobId() == null) {
            throw new IllegalArgumentException("Job ID cannot be null");
        }
        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        EligibilityRule rule = new EligibilityRule(dto.getRuleName(), dto.getCriteria(), dto.getRuleValue(), job);
        return ruleRepository.save(rule);
    }

    public List<EligibilityRule> getRulesByJob(Long jobId) {
        return ruleRepository.findByJobId(jobId);
    }

    public void deleteRule(Long ruleId) {
        if (ruleId == null) {
            throw new IllegalArgumentException("Rule ID cannot be null");
        }
        EligibilityRule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        ruleRepository.delete(rule);
    }

    public Application updateApplicationStatus(Long applicationId, String status) {
        if (applicationId == null) {
            throw new IllegalArgumentException("Application ID cannot be null");
        }
        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        String newStatus = status.toUpperCase();
        app.setStatus(newStatus);
        Application savedApp = applicationRepository.save(app);

        // Auto-generate notification for applicant
        String userEmail = savedApp.getUser().getEmail();
        String jobTitle = savedApp.getJob().getTitle();
        String notificationTitle = "Application Status Update";
        String notificationMessage = String.format("Your application for '%s' has been updated to: %s", jobTitle, newStatus);

        notificationService.sendNotification(userEmail, notificationTitle, notificationMessage);

        return savedApp;
    }
}