package com.govnext.backend.controller;

import com.govnext.backend.dto.EligibilityRuleDto;
import com.govnext.backend.entity.Application;
import com.govnext.backend.entity.EligibilityRule;
import com.govnext.backend.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@SuppressWarnings("null")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/rules")
    public ResponseEntity<EligibilityRule> createRule(@RequestBody EligibilityRuleDto dto) {
        return ResponseEntity.ok(adminService.addRule(dto));
    }

    @GetMapping("/rules/job/{jobId}")
    public ResponseEntity<List<EligibilityRule>> getRulesByJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(adminService.getRulesByJob(jobId));
    }

    @DeleteMapping("/rules/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        adminService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/applications/{id}/status")
    public ResponseEntity<Application> updateApplicationStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(adminService.updateApplicationStatus(id, status));
    }
}