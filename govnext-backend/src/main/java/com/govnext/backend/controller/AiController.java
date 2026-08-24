package com.govnext.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/ai")
@SuppressWarnings("null")
public class AiController {

    private final RestClient pythonClient = RestClient.create("http://localhost:8000");

    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> proxyChatToPython(@RequestBody Object chatRequest) {
        return pythonClient.post()
                .uri("/routes/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .body(chatRequest)
                .retrieve()
                .toEntity(String.class);
    }

    @PostMapping(value = "/resume", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> proxyResumeToPython(@RequestBody Object resumeRequest) {
        return pythonClient.post()
                .uri("/routes/resume")
                .contentType(MediaType.APPLICATION_JSON)
                .body(resumeRequest)
                .retrieve()
                .toEntity(String.class);
    }

    @PostMapping(value = "/ocr", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> proxyOcrToPython(@RequestBody Object ocrRequest) {
        return pythonClient.post()
                .uri("/routes/ocr")
                .contentType(MediaType.APPLICATION_JSON)
                .body(ocrRequest)
                .retrieve()
                .toEntity(String.class);
    }
}