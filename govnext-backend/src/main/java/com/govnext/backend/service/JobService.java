package com.govnext.backend.service;

import com.govnext.backend.dto.JobDto;
import com.govnext.backend.entity.Job;
import com.govnext.backend.exception.ResourceNotFoundException;
import com.govnext.backend.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));
    }

    public Job createJob(JobDto jobDto) {
        Job job = new Job();
        job.setTitle(jobDto.getTitle());
        job.setDepartment(jobDto.getDepartment());
        job.setDescription(jobDto.getDescription());
        job.setLocation(jobDto.getLocation());
        job.setSalary(jobDto.getSalary());
        return jobRepository.save(job);
    }

    public List<Job> searchJobs(String query) {
        return jobRepository.findByTitleContainingIgnoreCaseOrDepartmentContainingIgnoreCase(query, query);
    }
}