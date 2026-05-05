package com.resume.Backend.Service.resume;

import com.resume.Backend.Service.parsing.ParsingService;
import com.resume.Backend.Repository.ResumeRepo;
import com.resume.Backend.Service.storage.FileStoringService;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class ResumeService {

    private final ResumeRepo repo;
    private final ParsingService parser;
    private final FileStoringService fileStoring;

    ResumeService(ResumeRepo repo, ParsingService parser, FileStoringService fileStoring) {
        this.repo = repo;
        this.parser =parser;
        this.fileStoring = fileStoring;
    }


    public String uploadResume(MultipartFile file) throws IOException{

        String fileName = file.getOriginalFilename();

        isResumeValid(file);

        assert fileName != null;
        String newFileName = fileStoring.createUniqueFileName(fileName);

        File destination = fileStoring.storeFileInServerFile(newFileName, file);

        String parsedText = parser.extractStringFromFile(destination);

        return parsedText;
    }

    public void isResumeValid(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        long size = file.getSize();

        double fileSize = (double) size / (1024 * 1024);

        if(fileSize >= 5) {
            throw new RuntimeException("File size too large");
        }

        if(file.isEmpty()) {
            throw new RuntimeException("File is Empty, again upload resume");
        }

//        assert fileName != null;
        if(fileName.isEmpty()) {
            throw new RuntimeException("No File Name");
        }

    }





}
