package com.govnext.backend.service;

import com.govnext.backend.dto.EligibilityRequest;
import com.govnext.backend.dto.EligibilityResponse;
import com.govnext.backend.entity.Scheme;
import com.govnext.backend.repository.SchemeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchemeService {

    private final SchemeRepository schemeRepository;

    public SchemeService(SchemeRepository schemeRepository) {
        this.schemeRepository = schemeRepository;
    }

    public List<Scheme> getAllSchemes() {
        return schemeRepository.findAll();
    }

    public Optional<Scheme> getSchemeById(Long id) {
        return schemeRepository.findById(id);
    }

    public Scheme createScheme(Scheme scheme) {
        return schemeRepository.save(scheme);
    }

    public List<EligibilityResponse> evaluateEligibility(EligibilityRequest request) {
        List<Scheme> allSchemes = schemeRepository.findAll();
        List<EligibilityResponse> results = new ArrayList<>();

        for (Scheme scheme : allSchemes) {
            int score = 100;
            List<String> matchReasons = new ArrayList<>();
            List<String> disqualificationReasons = new ArrayList<>();

            // Age evaluation
            if (request.getAge() != null) {
                if (scheme.getMinAge() != null && request.getAge() < scheme.getMinAge()) {
                    score -= 35;
                    disqualificationReasons.add("Age is below minimum requirement of " + scheme.getMinAge());
                } else if (scheme.getMaxAge() != null && request.getAge() > scheme.getMaxAge()) {
                    score -= 35;
                    disqualificationReasons.add("Age exceeds maximum threshold of " + scheme.getMaxAge());
                } else {
                    matchReasons.add("Meets age eligibility requirement");
                }
            }

            // Income evaluation
            if (request.getAnnualIncome() != null && scheme.getMaxIncome() != null) {
                if (request.getAnnualIncome() > scheme.getMaxIncome()) {
                    score -= 40;
                    disqualificationReasons.add("Income exceeds maximum limit of ₹" + scheme.getMaxIncome());
                } else {
                    matchReasons.add("Income is within eligible threshold");
                }
            }

            // Gender evaluation
            if (request.getGender() != null && scheme.getTargetGender() != null && !"ALL".equalsIgnoreCase(scheme.getTargetGender())) {
                if (!scheme.getTargetGender().equalsIgnoreCase(request.getGender())) {
                    score -= 25;
                    disqualificationReasons.add("Scheme is tailored for gender: " + scheme.getTargetGender());
                } else {
                    matchReasons.add("Matches targeted gender criterion");
                }
            }

            // State residency evaluation
            if (request.getState() != null && scheme.getRequiredState() != null && !"ALL".equalsIgnoreCase(scheme.getRequiredState())) {
                if (!scheme.getRequiredState().equalsIgnoreCase(request.getState())) {
                    score -= 25;
                    disqualificationReasons.add("Restricted to state residents of " + scheme.getRequiredState());
                } else {
                    matchReasons.add("Matches state residency requirement");
                }
            }

            score = Math.max(0, score);
            boolean isEligible = disqualificationReasons.isEmpty();

            results.add(EligibilityResponse.builder()
                    .scheme(scheme)
                    .eligible(isEligible)
                    .matchScore(score)
                    .matchReasons(matchReasons)
                    .disqualificationReasons(disqualificationReasons)
                    .build());
        }

        return results;
    }
}