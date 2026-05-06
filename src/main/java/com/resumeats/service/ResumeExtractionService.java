package com.resumeats.service;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeExtractionService {
    public String extractTextFromFile(MultipartFile file) throws Exception {
        Tika tika = new Tika();
        return tika.parseToString(file.getInputStream());
    }
}