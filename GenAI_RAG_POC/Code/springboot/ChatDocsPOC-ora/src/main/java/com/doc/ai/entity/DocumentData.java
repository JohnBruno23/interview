package com.doc.ai.entity;


/*-----------------------------------------------------------------------
 * Coded By : John
 * Date : 17/11/2026
 * Purpose : DocumentData 
 *-----------------------------------------------------------------------*/
import lombok.Data;

@Data
public class DocumentData {

    private Long id;
    private String fileName;
    private String content;
}