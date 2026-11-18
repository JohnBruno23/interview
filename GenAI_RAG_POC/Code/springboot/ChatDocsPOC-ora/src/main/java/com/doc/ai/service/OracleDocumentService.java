package com.doc.ai.service;


import lombok.RequiredArgsConstructor;
import oracle.jdbc.OracleConnection;
import oracle.sql.VECTOR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class OracleDocumentService {

	  private  JdbcTemplate jdbcTemplate = new JdbcTemplate();
	    
		 @Autowired
		 private GeminiEmbeddingService embeddingService;

    public String saveDocument(String fileName,
                               String content) throws Exception {

        float[] embedding =
                embeddingService.generateEmbedding(content);

        Connection connection =
                jdbcTemplate.getDataSource()
                        .getConnection();

        OracleConnection oracleConnection =
                connection.unwrap(OracleConnection.class);
        
        
        String vectorString = Arrays.toString(embedding);

        String sql = """
            INSERT INTO DOCUMENT_STORE
            (FILE_NAME, CONTENT, EMBEDDING)
            VALUES (?, ?, TO_VECTOR(?))
            """;

        jdbcTemplate.update(
            sql,
            fileName,
            content,
            vectorString
        );
        

        

        return "Document Stored Successfully";
    }
}