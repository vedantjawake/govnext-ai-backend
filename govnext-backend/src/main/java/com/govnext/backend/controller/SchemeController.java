package com.govnext.backend.controller;

import com.govnext.backend.dto.EligibilityRequest;
import com.govnext.backend.dto.EligibilityResponse;
import com.govnext.backend.entity.Scheme;
import com.govnext.backend.service.SchemeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schemes")
@SuppressWarnings("null")
public class SchemeController {

    private final SchemeService schemeService;

    public SchemeController(SchemeService schemeService) {
        this.schemeService = schemeService;
    }

    @GetMapping
    public ResponseEntity<List<Scheme>> getAllSchemes() {
        return ResponseEntity.ok(schemeService.getAllSchemes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scheme> getSchemeById(@PathVariable Long id) {
        return schemeService.getSchemeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Scheme> createScheme(@RequestBody Scheme scheme) {
        return ResponseEntity.status(HttpStatus.CREATED).body(schemeService.createScheme(scheme));
    }

    @PostMapping("/evaluate")
    public ResponseEntity<List<EligibilityResponse>> evaluateEligibility(@RequestBody EligibilityRequest request) {
        return ResponseEntity.ok(schemeService.evaluateEligibility(request));
    }
}