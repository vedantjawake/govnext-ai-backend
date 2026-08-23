package com.govnext.backend.controller;

import com.govnext.backend.dto.JobDto;
import com.govnext.backend.entity.Job;
import com.govnext.backend.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam("q") String query) {
        return ResponseEntity.ok(jobService.searchJobs(query));
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody JobDto jobDto) {
        return ResponseEntity.ok(jobService.createJob(jobDto));
    }
}