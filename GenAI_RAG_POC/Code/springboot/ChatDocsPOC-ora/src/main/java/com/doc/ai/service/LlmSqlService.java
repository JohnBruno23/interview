package com.doc.ai.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LlmSqlService {

    private final GeminiOnPremService geminiService;
    private final SqlExecutionService sqlExecutionService;

    public LlmSqlService(
    		GeminiOnPremService geminiService,
            SqlExecutionService sqlExecutionService) {

        this.geminiService = geminiService;
        this.sqlExecutionService = sqlExecutionService;
    }

    public Map<String,Object> process(String question) {

        String sql = geminiService.generateSql(question);
        
        sql = cleanSql(sql);
        
        

        List<Map<String,Object>> result =
                sqlExecutionService.execute(sql);

        return Map.of(
                "question", question,
                "generatedSql", sql,
                "data", result
        );
    }
    
    /*------------Remove AI generated Additional String -------------*/
    
    private String cleanSql(String sql) {

        sql = sql.replace("```sql", "");
        sql = sql.replace("```", "");
        sql = sql.trim();

        if (sql.endsWith(";")) {
            sql = sql.substring(0, sql.length() - 1);
        }

        return sql;
    }
    
}