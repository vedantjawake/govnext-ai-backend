package com.govnext.backend.repository;

import com.govnext.backend.entity.EligibilityRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EligibilityRuleRepository extends JpaRepository<EligibilityRule, Long> {
}