package com.resume.Backend.service.resume_main;

import com.resume.Backend.DTO.ResponseDTO;
import com.resume.Backend.DTO.ResumeDataDTO;
import com.resume.Backend.ExceptionHandling.customexception.InvalidFileException;
import com.resume.Backend.model.ResumeEntityModel;
import com.resume.Backend.service.jobMatching.JobMatchingService;
import com.resume.Backend.service.extraction.ExtractionService;
import com.resume.Backend.service.parsing.ParsingService;
import com.resume.Backend.repository.ResumeRepo;
import com.resume.Backend.service.storingInDB.FileStoringService;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class ResumeService {

    private final ResumeRepo repo;
    private final ParsingService parser;
    private final FileStoringService fileStoring;
    private final ExtractionService extractionService;
    private final JobMatchingService jobMatchingService;

    ResumeService(ResumeRepo repo, ParsingService parser, ExtractionService extractionService,
                  FileStoringService fileStoring, JobMatchingService jobMatchingService) {
        this.repo = repo;
        this.parser =parser;
        this.fileStoring = fileStoring;
        this.extractionService = extractionService;
        this.jobMatchingService = jobMatchingService;
    }

    public void isResumeValid(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        long size = file.getSize();

        double fileSize = (double) size / (1024 * 1024);

        if(fileSize >= 5) {
            throw new InvalidFileException("File size too large. Up to 5mb size file is allowed.");
        }

        if(file.isEmpty()) {
            throw new InvalidFileException("File is Empty, again upload resume file with content");
        }

        assert fileName != null;
        if(fileName.isEmpty()) {
            throw new InvalidFileException("No File Name, upload a proper file with name");
        }

    }

    public List<ResponseDTO> uploadResume(MultipartFile file){

        String fileName = file.getOriginalFilename();

        isResumeValid(file);

        String newFileName = fileStoring.createUniqueFileName(fileName);
        File destination = fileStoring.storeFileInServerFile(newFileName, file);
        String parsedText = parser.extractStringFromFile(destination);
        ResumeDataDTO resumeData = extractionService.createDTO(parsedText);

        return jobMatchingService.matchTheJobs(resumeData.getSkills());
    }

    public ResumeDataDTO saveInDB(ResumeDataDTO resumeData) {
        ResumeEntityModel resumeEntity = convertToResumeEntity(resumeData);
        repo.save(resumeEntity);
        return resumeData;
    }

    public ResumeEntityModel convertToResumeEntity(ResumeDataDTO resumeData) {
        ResumeEntityModel resumeEntity = new ResumeEntityModel();

        resumeEntity.setEmail(resumeData.getEmail());
        resumeEntity.setPhoneNo(resumeData.getPhoneNo());
        resumeEntity.setSkills(resumeData.getSkills());

        return resumeEntity;
    }

}
