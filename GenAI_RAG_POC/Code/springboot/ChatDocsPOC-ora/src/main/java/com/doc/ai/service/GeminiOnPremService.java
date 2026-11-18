package com.doc.ai.service;


import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeminiOnPremService {

    private final Client client;
    
    @Value("${gemini.model}")
    private String model;
    

    public GeminiOnPremService(@Value("${gemini.api.key}") String apiKey) {
        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public String generateSql(String question) {

    	
    	/*
        String prompt = """
                You are Oracle SQL Expert.

                Table Structure:

                EMP(
                  ID NUMBER,
                  ENAME VARCHAR2(100),
                  JOB VARCHAR2(100),
                  SAL NUMBER,
                  DEPTNO NUMBER
                )

                Generate only Oracle SQL.
                Do not return explanation.

                Question:
                """ + question;
                */
    	
    	/*-----------Include the Condition in prompt Engg --------*/
    	String prompt = """
    			You are an Oracle SQL expert.

    			Return ONLY a valid Oracle SQL query.

    			Rules:
    			1. Do not use markdown.
    			2. Do not use ```sql.
    			3. Do not include explanations.
    			4. Do not include comments.
    			5. Return only executable SQL.
    			6. Generate only Oracle SQL.

    			Table:
    			EMP(
    			 ID NUMBER,
    			 ENAME VARCHAR2(100),
    			 JOB VARCHAR2(100),
    			 SAL NUMBER,
    			 DEPTNO NUMBER
    			)

    			Question:
    			%s
    			""".formatted(question);

        GenerateContentResponse response =
                client.models.generateContent(
                		model,
                        prompt,
                        null);

        return response.text().trim();
    }
}