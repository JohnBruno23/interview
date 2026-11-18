package com.doc.ai.service;


import com.google.genai.Client;
import com.google.genai.types.EmbedContentConfig;
import com.google.genai.types.EmbedContentResponse;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class GeminiEmbeddingService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public float[] generateEmbedding(String text) {
    	
    	
    	// String apiKey = "YOUR_GEMINI_API_KEY";
        String text1 = "How does semantic search work?";

   
    	Client client = Client.builder().apiKey(apiKey).build();
    	
    	
    	// Optional: Configure task type or output dimensions
        EmbedContentConfig config = EmbedContentConfig.builder()
                .taskType("RETRIEVAL_DOCUMENT")
                .outputDimensionality(768)
                .build();
        
     // Call embedContent (pass null instead of config if using defaults)
        EmbedContentResponse response = client.models.embedContent(
                "gemini-embedding-001",
                text,
                config
        );
        float[]  values =  getEmbeddingVector(response);
        return values;
    }
    
    
    
    
    
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