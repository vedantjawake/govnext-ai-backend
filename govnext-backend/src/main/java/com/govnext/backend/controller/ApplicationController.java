package com.govnext.backend.controller;

import com.govnext.backend.dto.ApplicationDto;
import com.govnext.backend.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationDto.Response> apply(@RequestBody ApplicationDto.Request request, Authentication authentication) {
        return ResponseEntity.ok(applicationService.applyForJob(authentication.getName(), request.getJobId()));
    }

    @GetMapping("/my-applications")
    public ResponseEntity<List<ApplicationDto.Response>> getMyApplications(Authentication authentication) {
        return ResponseEntity.ok(applicationService.getUserApplications(authentication.getName()));
    }
}