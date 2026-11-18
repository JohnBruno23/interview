package com.doc.ai.service;

/*-----------------------------------------------------------------------
 * Coded By : John
 * Date : 17/11/2026
 * Purpose : RagService
 *-----------------------------------------------------------------------*/

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doc.ai.entity.DocumentStore;
import com.doc.ai.repo.DocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RagService {

   // private final GeminiService geminiService;
   // private final DocumentRepository repository;

    
    
    @Autowired
	 private GeminiService geminiService;
    
    @Autowired
	 private DocumentRepository repository;
    
    public String askQuestion(String question) {

        List<DocumentStore> docs =
                repository.findAll();

        String context = docs.stream()
                        .map(DocumentStore::getContent)
                        .collect(Collectors.joining("\n"));

        String prompt = """
                You are a helpful assistant.

                Use only the provided context.

                Context:
                %s

                Question:
                %s

                If the answer is not in the context,
                say "Information not found in documents".
                """
                .formatted(context, question);

        return geminiService.askGemini(prompt);
    }
}