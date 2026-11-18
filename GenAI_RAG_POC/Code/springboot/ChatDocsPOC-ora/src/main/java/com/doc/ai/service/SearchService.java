package com.doc.ai.service;

/*-----------------------------------------------------------------------
 * Coded By : John
 * Date : 17/11/2026
 * Purpose : SearchService
 *-----------------------------------------------------------------------*/

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<String> search(String vectorLiteral) {

        String sql = """
                SELECT CONTENT
                FROM DOCUMENT_STORE
                ORDER BY VECTOR_DISTANCE(
                            EMBEDDING,
                            TO_VECTOR(?),
                            COSINE)
                FETCH FIRST 5 ROWS ONLY
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getString("CONTENT"),
                vectorLiteral
        );
    }
}