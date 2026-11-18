package com.doc.ai.dto;

/*-----------------------------------------------------------------------
 * Coded By : John
 * Date : 17/11/2026
 * Purpose : File upload response pojo
 *-----------------------------------------------------------------------*/


public class UploadResponse {

    private String fileName;
    private String filePath;

    public UploadResponse() {
    }

    public UploadResponse(String fileName,
                          String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
