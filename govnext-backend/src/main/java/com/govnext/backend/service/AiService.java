package com.govnext.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ai.service.url:http://localhost:8000}")
    private String aiServiceUrl;

    public Map<String, Object> getAiChatResponse(String prompt) {
        String url = aiServiceUrl + "/chat";
        
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("prompt", prompt);

        // Forward request to Python FastApi/Flask service
        return restTemplate.postForObject(url, requestBody, Map.class);
    }
}