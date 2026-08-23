package com.govnext.backend.service;

import com.govnext.backend.dto.ApplicationDto;
import com.govnext.backend.entity.Application;
import com.govnext.backend.entity.Job;
import com.govnext.backend.entity.User;
import com.govnext.backend.exception.ResourceNotFoundException;
import com.govnext.backend.repository.ApplicationRepository;
import com.govnext.backend.repository.JobRepository;
import com.govnext.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public ApplicationService(ApplicationRepository applicationRepository, JobRepository jobRepository, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public ApplicationDto.Response applyForJob(String userEmail, Long jobId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        Application application = new Application();
        application.setUser(user);
        application.setJob(job);
        application.setStatus("PENDING");
        application.setEligibilityScore(85.0); // Placeholder for AI Matching engine integration

        Application saved = applicationRepository.save(application);
        return new ApplicationDto.Response(saved.getId(), job.getId(), job.getTitle(), saved.getStatus(), saved.getEligibilityScore());
    }

    public List<ApplicationDto.Response> getUserApplications(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return applicationRepository.findByUserId(user.getId()).stream()
                .map(app -> new ApplicationDto.Response(
                        app.getId(),
                        app.getJob().getId(),
                        app.getJob().getTitle(),
                        app.getStatus(),
                        app.getEligibilityScore()))
                .collect(Collectors.toList());
    }
}