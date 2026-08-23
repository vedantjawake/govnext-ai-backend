package com.govnext.backend.repository;

import com.govnext.backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTitleContainingIgnoreCaseOrDepartmentContainingIgnoreCase(String title, String department);
}