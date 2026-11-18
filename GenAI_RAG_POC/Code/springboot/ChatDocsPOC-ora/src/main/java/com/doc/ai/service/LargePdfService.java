package com.doc.ai.service;

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LargePdfService {

    public String extract(MultipartFile file)
            throws IOException {

        StringBuilder text =
                new StringBuilder();

        try (PDDocument document =
                     Loader.loadPDF(
                             file.getBytes())) {

            PDFTextStripper stripper =
                    new PDFTextStripper();

            int totalPages =
                    document.getNumberOfPages();

            for (int page = 1;
                 page <= totalPages;
                 page++) {

                stripper.setStartPage(page);
                stripper.setEndPage(page);

                text.append(
                        stripper.getText(document));
            }
        }

        return text.toString();
    }
}