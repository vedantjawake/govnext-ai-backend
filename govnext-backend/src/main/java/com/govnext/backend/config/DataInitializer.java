package com.govnext.backend.config;

import com.govnext.backend.entity.Job;
import com.govnext.backend.entity.Scheme;
import com.govnext.backend.repository.JobRepository;
import com.govnext.backend.repository.SchemeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(JobRepository jobRepository, SchemeRepository schemeRepository) {
        return args -> {
            if (jobRepository.count() == 0) {
                Job job1 = new Job("Assistant Section Officer", "Ministry of External Affairs", 
                        "Graduate in any discipline. Age 20-30 years.", "Delhi", 65000.0);
                Job job2 = new Job("Junior Engineer (Civil)", "Central Public Works Dept", 
                        "Diploma or Degree in Civil Engineering.", "Pan India", 45000.0);
                
                jobRepository.save(job1);
                jobRepository.save(job2);
                System.out.println(">>> Sample Jobs Seeded Successfully!");
            }

            if (schemeRepository.count() == 0) {
                Scheme scheme1 = new Scheme();
                scheme1.setName("PM Internship Scheme");
                scheme1.setCategory("Skill Development");
                scheme1.setDescription("Provides internship opportunities in top 500 companies.");
                scheme1.setBenefits("Monthly stipend of Rs 5,000 + One-time allowance Rs 6,000.");
                scheme1.setMinAge(21);
                
                schemeRepository.save(scheme1);
                System.out.println(">>> Sample Schemes Seeded Successfully!");
            }
        };
    }
}