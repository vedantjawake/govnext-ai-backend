package com.govnext.backend.dto;

import com.govnext.backend.entity.Scheme;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EligibilityResponse {
    private Scheme scheme;
    private boolean eligible;
    private int matchScore; // 0 to 100 percentage
    private List<String> matchReasons;
    private List<String> disqualificationReasons;
}