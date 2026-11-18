package com.doc.ai.service;

/*-----------------------------------------------------------------------
 * Coded By : John
 * Date : 17/11/2026
 * Purpose : DocumentUploadService
 *-----------------------------------------------------------------------*/

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.OracleType;
import java.sql.Connection;

@Service
@RequiredArgsConstructor
public class DocumentUploadService {
    
    @Autowired
 	 private JdbcTemplate jdbcTemplate;
    
    @Autowired
	 private PdfService pdfService;
    
    @Autowired
	 private GeminiEmbeddingService1 embeddingService;

    public void upload(MultipartFile file)
            throws Exception {

        String content =
                pdfService.extract(file);
        
        float[]  embedding = embeddingService.createEmbedding(content);
        
        System.out.println("------------------------embedding String------------------------------------------");
        System.out.println(embedding.toString());
        System.out.println("------------------------------------------------------------------");
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        String sql = "INSERT INTO DOCUMENT_STORE (FILE_NAME, CONTENT, EMBEDDING) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, file.getOriginalFilename());
            pstmt.setString(2, content);
            
            // Bind float[] directly as an Oracle VECTOR (no string conversion or TO_VECTOR needed)
            pstmt.setObject(3, embedding, OracleType.VECTOR);
            
            pstmt.executeUpdate();
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Document uploaded and stored successfully.");
        System.out.println("------------------------------------------------------------------");
    }
}