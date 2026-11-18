package com.doc.ai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SqlExecutionService {

 /*
    @Autowired
	 private JdbcTemplate jdbcTemplate;
*/
   
    private final JdbcTemplate jdbcTemplate;

    public SqlExecutionService (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Map<String,Object>> execute(String sql) {
    	
    	
    	
    	List<Map<String, Object>> result =
    	        jdbcTemplate.queryForList(sql);

    	System.out.println("----------------------------------------------------------------------------------");
    	System.out.println(sql);
    	System.out.println(result.size());
    	System.out.println("----------------------------------------------------------------------------------");
    	
        return result;
    }
}