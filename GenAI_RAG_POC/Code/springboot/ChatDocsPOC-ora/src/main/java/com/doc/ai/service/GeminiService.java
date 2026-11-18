package com.doc.ai.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class GeminiService {

    private final Client client;
    
    @Value("${gemini.model}")
    private String model;

    public GeminiService(
            @Value("${gemini.api.key}") String apiKey) {

        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public String askGemini(String prompt) {

        try {

            GenerateContentResponse response =
                    client.models.generateContent(
                    		model,
                            prompt,
                            null);

            return response.text();

        } catch (Exception ex) {

            ex.printStackTrace();

            return "Error: " + ex.getMessage();
        }
    }
}
