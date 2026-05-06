package com.resume.Backend.service.resume;

import com.resume.Backend.DTO.ResumeDataDTO;
import com.resume.Backend.model.ResumeEntityModel;
import com.resume.Backend.service.extraction.ExtractionService;
import com.resume.Backend.service.parsing.ParsingService;
import com.resume.Backend.repository.ResumeRepo;
import com.resume.Backend.service.storage.FileStoringService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;


@Service
public class ResumeService {

    private final ResumeRepo repo;
    private final ParsingService parser;
    private final FileStoringService fileStoring;
    private final ExtractionService extractionService;

    ResumeService(ResumeRepo repo, ParsingService parser, ExtractionService extractionService, FileStoringService fileStoring) {
        this.repo = repo;
        this.parser =parser;
        this.fileStoring = fileStoring;
        this.extractionService = extractionService;
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

    public ResumeEntityModel uploadResume(MultipartFile file) throws IOException{

        String fileName = file.getOriginalFilename();

        isResumeValid(file);

        String newFileName = fileStoring.createUniqueFileName(fileName);

        File destination = fileStoring.storeFileInServerFile(newFileName, file);

        String parsedText = parser.extractStringFromFile(destination);

        ResumeDataDTO resumeData = extractionService.createDTO(parsedText);

        return saveInDB(resumeData);
    }

    public ResumeEntityModel saveInDB(ResumeDataDTO resumeData) {
        ResumeEntityModel resumeEntity = mapToEntity(resumeData);

        return repo.save(resumeEntity);
    }

    public ResumeEntityModel mapToEntity(ResumeDataDTO resumeData) {
        ResumeEntityModel resumeEntity = new ResumeEntityModel();

        resumeEntity.setEmail(resumeData.getEmail());
        resumeEntity.setPhoneNo(resumeData.getPhoneNo());
        resumeEntity.setSkills(resumeData.getSkills());

        return resumeEntity;
    }

}
