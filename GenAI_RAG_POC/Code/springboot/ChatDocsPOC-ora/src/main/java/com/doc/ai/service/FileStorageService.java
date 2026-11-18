package com.doc.ai.service;
/*-----------------------------------------------------------------------
 * Coded By : John
 * Date : 17/11/2026
 * Purpose : FileStorageService
 *-----------------------------------------------------------------------*/


import com.doc.ai.config.FileStorageProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {

    private final Path uploadPath;

    public FileStorageService(FileStorageProperties properties) {

        this.uploadPath =
                Paths.get(properties.getUploadDir())
                        .toAbsolutePath()
                        .normalize();

        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder");
        }
    }

    public String storeFile(MultipartFile file) {

        try {

            String fileName =
                    System.currentTimeMillis() +
                    "_" +
                    file.getOriginalFilename();

            Path target =
                    uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    target,
                    StandardCopyOption.REPLACE_EXISTING);

            return target.toString();

        } catch (IOException ex) {
            throw new RuntimeException(
                    "Failed to store file", ex);
        }
    }
}
