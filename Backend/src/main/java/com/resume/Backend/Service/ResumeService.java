package com.resume.Backend.Service;

import com.resume.Backend.Repository.ResumeRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ResumeService {

    private final ResumeRepo repo;

    ResumeService(ResumeRepo repo) {
        this.repo = repo;
    }

    public String uploadResume(MultipartFile resFile) throws IOException {

        String fileName = resFile.getOriginalFilename();

        long size = resFile.getSize();

        double fileSize = (double) size / (1024 * 1024);

        if(fileSize >= 5) {
            throw new RuntimeException("File size too large");
        }

        if(resFile.isEmpty()) {
            throw new RuntimeException("File is Empty, again upload resume");
        }

//        assert fileName != null;
        if(fileName.isEmpty()) {
            throw new RuntimeException("No File Name");
        }

        LocalDateTime time = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");

        String timeStamp = time.format(formatter);

        String uuId = UUID.randomUUID().toString().replace("-", "");

        // Handle NullPointerException
        int idxOfDot = fileName.lastIndexOf(".");

        if(idxOfDot == -1) {
            throw new RuntimeException("No File extension, provide proper file.");
        }

        // Gives the name without taking extensions
        String name = fileName.substring(0, idxOfDot);

        // Stores the extensions, and converts to lowercase
        String extension = fileName.substring(idxOfDot).toLowerCase();

        String newFileName;     // new name for file, before storing it.

        // Checks whether the entered resume is supported format or not
        if(!(extension.equals(".pdf") || extension.equals(".doc") || extension.equals(".docx"))) {
            throw new RuntimeException("Format not supporting, upload pdf, doc or docx file");
        }
        newFileName = name + "_" + timeStamp + "_" + uuId + extension;






        return "Resume File is Uploaded Successfully";
    }



}
