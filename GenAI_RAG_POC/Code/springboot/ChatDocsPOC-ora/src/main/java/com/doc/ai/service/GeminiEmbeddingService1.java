package com.doc.ai.service;

import com.google.genai.Client;
import com.google.genai.types.EmbedContentConfig;
import com.google.genai.types.EmbedContentResponse;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeminiEmbeddingService1 {

    private final Client client;

    //------------Constructor ----------------
    public GeminiEmbeddingService1(
            @Value("${gemini.api.key}") String apiKey) {

    	System.out.println("---------------------------Embeding Constructor-----------------------------------------------");
    	System.out.println("GeminiEmbeddingService1 initialized with API Key: " + apiKey);
    	System.out.println("--------------------------------------------------------------------------");
    	
        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

     //----Create Embedding Method ----------------
    public float[]  createEmbedding(String text) {
    	System.out.println("--------------------------Embeding text------------------------------------------------");
    	System.out.println("GeminiEmbeddingService1 createEmbedding triggered for the text: " + text);
    	System.out.println("--------------------------------------------------------------------------");
    	
    	/*
    	client.models.list().forEach(model -> {
            System.out.println(model.name());
            System.out.println("Supported methods: " + model.supportedActions());
            System.out.println("--------------------------------");
        });

    	*/
    	
        EmbedContentResponse response =
                client.models.embedContent(
                        "gemini-embedding-001",
                        text,
                        null);

        float[]  values =  getEmbeddingVector(response);
       
        /*
        // Convert float[] to List<Float>
        List<Float> embeddingList = new java.util.ArrayList<>(values.length);
        for (float value : values) {
			embeddingList.add(value);
		}
		return embeddingList;
		*/
        
        return values;
    }
    
    //----Get Embedding Vector Method ----------------
    public float[] getEmbeddingVector(EmbedContentResponse response) {
        return response.embeddings()
                .filter(embeddings -> !embeddings.isEmpty())
                .flatMap(embeddings -> embeddings.get(0).values())
                .map(vector -> {
                    float[] values = new float[vector.size()];
                    for (int i = 0; i < vector.size(); i++) {
                        values[i] = vector.get(i);
                    }
                    return values; // Returns float[] from the .map() lambda
                })
                .orElse(new float[0]); // Fallback if embeddings are empty or missing
    }
}