package com.govnext.backend.dto;

public class ApplicationDto {

    public static class Request {
        private Long jobId;

        public Long getJobId() { return jobId; }
        public void setJobId(Long jobId) { this.jobId = jobId; }
    }

    public static class Response {
        private Long applicationId;
        private Long jobId;
        private String jobTitle;
        private String status;
        private Double eligibilityScore;

        public Response(Long applicationId, Long jobId, String jobTitle, String status, Double eligibilityScore) {
            this.applicationId = applicationId;
            this.jobId = jobId;
            this.jobTitle = jobTitle;
            this.status = status;
            this.eligibilityScore = eligibilityScore;
        }

        public Long getApplicationId() { return applicationId; }
        public Long getJobId() { return jobId; }
        public String getJobTitle() { return jobTitle; }
        public String getStatus() { return status; }
        public Double getEligibilityScore() { return eligibilityScore; }
    }
}