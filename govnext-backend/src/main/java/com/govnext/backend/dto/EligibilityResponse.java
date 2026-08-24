package com.govnext.backend.dto;

import com.govnext.backend.entity.Scheme;
import java.util.List;

public class EligibilityResponse {
    private Scheme scheme;
    private boolean eligible;
    private int matchScore; // 0 to 100 percentage
    private List<String> matchReasons;
    private List<String> disqualificationReasons;

    public EligibilityResponse() {}

    public EligibilityResponse(Scheme scheme, boolean eligible, int matchScore, List<String> matchReasons, List<String> disqualificationReasons) {
        this.scheme = scheme;
        this.eligible = eligible;
        this.matchScore = matchScore;
        this.matchReasons = matchReasons;
        this.disqualificationReasons = disqualificationReasons;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    public List<String> getMatchReasons() {
        return matchReasons;
    }

    public void setMatchReasons(List<String> matchReasons) {
        this.matchReasons = matchReasons;
    }

    public List<String> getDisqualificationReasons() {
        return disqualificationReasons;
    }

    public void setDisqualificationReasons(List<String> disqualificationReasons) {
        this.disqualificationReasons = disqualificationReasons;
    }

    public static EligibilityResponseBuilder builder() {
        return new EligibilityResponseBuilder();
    }

    public static class EligibilityResponseBuilder {
        private Scheme scheme;
        private boolean eligible;
        private int matchScore;
        private List<String> matchReasons;
        private List<String> disqualificationReasons;

        EligibilityResponseBuilder() {}

        public EligibilityResponseBuilder scheme(Scheme scheme) {
            this.scheme = scheme;
            return this;
        }

        public EligibilityResponseBuilder eligible(boolean eligible) {
            this.eligible = eligible;
            return this;
        }

        public EligibilityResponseBuilder matchScore(int matchScore) {
            this.matchScore = matchScore;
            return this;
        }

        public EligibilityResponseBuilder matchReasons(List<String> matchReasons) {
            this.matchReasons = matchReasons;
            return this;
        }

        public EligibilityResponseBuilder disqualificationReasons(List<String> disqualificationReasons) {
            this.disqualificationReasons = disqualificationReasons;
            return this;
        }

        public EligibilityResponse build() {
            return new EligibilityResponse(scheme, eligible, matchScore, matchReasons, disqualificationReasons);
        }
    }
}