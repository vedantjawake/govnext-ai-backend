package com.govnext.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schemes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String benefits;

    private Integer minAge;
    private Integer maxAge;
    private Double maxIncome;
    private String targetGender; // "ALL", "MALE", "FEMALE"
    private String requiredState; // "ALL" or specific state
}