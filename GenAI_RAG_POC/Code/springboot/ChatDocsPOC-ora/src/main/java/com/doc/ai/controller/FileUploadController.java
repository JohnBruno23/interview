package com.doc.ai.controller;

/*-----------------------------------------------------------------------
 * Coded By : John
 * Date : 17/11/2026
 * Purpose : File upload Controller
 *-----------------------------------------------------------------------*/

import com.doc.ai.dto.UploadResponse;
import com.doc.ai.service.DocumentUploadService;
import com.doc.ai.service.FileStorageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@Tag(name = "File Upload API")
public class FileUploadController {

    private final FileStorageService fileStorageService;
    
    @Autowired
  	 private DocumentUploadService fileVectorService;

    public FileUploadController(
            FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(
            value = "/upload2",
            consumes = "multipart/form-data")
    @Operation(
            summary = "Upload File",
            description = "Upload file and save it to local folder")
    public ResponseEntity<UploadResponse> uploadFile(
            @RequestPart("file")
            MultipartFile file) {

        String storedPath =
                fileStorageService.storeFile(file);

        UploadResponse response =
                new UploadResponse(
                        file.getOriginalFilename(),
                        storedPath);

        
        
        try {
			fileVectorService.upload(file);
			System.out.println("File uploaded and vector stored successfully.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return ResponseEntity.ok(response);
    }
}