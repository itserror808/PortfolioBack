package com.example.portfolioback.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
public class ResumeDownloaderController {
    @GetMapping("/download-resume")
    public ResponseEntity<InputStreamResource> downloadResume() {
        try {
            System.out.println("Attempt to download file: ");
            String resumeFilePath = "/home/meftah/PortfolioBack/src/main/resources/assets/MEFTAH AhmedReda Resume.pdf"; // Replace with the actual file path
            File resumeFile = new File(resumeFilePath);
            InputStream inputStream = new FileInputStream(resumeFile);
            InputStreamResource resource = new InputStreamResource(inputStream);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (IOException e) {
            System.out.println("Error downloading file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}