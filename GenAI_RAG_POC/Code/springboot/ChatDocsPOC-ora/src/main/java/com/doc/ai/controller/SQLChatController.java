package com.doc.ai.controller;

import org.springframework.web.bind.annotation.*;

import com.doc.ai.dto.QuestionRequest;
import com.doc.ai.service.LlmSqlService;

import java.util.Map;

@RestController
@RequestMapping("/api/sqlchat")
public class SQLChatController {

    private final LlmSqlService service;

    public SQLChatController(LlmSqlService service) {
        this.service = service;
    }

    @PostMapping
    public Map<String,Object> ask(
            @RequestBody QuestionRequest request) {

        return service.process(request.getQuestion());
    }
}