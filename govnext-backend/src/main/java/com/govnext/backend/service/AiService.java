package com.govnext.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AiService {

    private final RestClient restClient;

    public AiService(@Value("${ai.service.url}") String aiServiceUrl) {
        this.restClient = RestClient.create(aiServiceUrl);
    }

    public String fetchChatResponse(Object chatPayload) {
        return restClient.post()
                .uri("/routes/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .body(chatPayload)
                .retrieve()
                .body(String.class);
    }
}