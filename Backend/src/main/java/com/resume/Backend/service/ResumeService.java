package com.resume.Backend.service;

import com.resume.Backend.dto.responseDto.ResumeAnalysisDto;
import com.resume.Backend.dto.ResumeDataDto;
import com.resume.Backend.exceptionhandling.customexception.InvalidFileException;
import com.resume.Backend.entity.ResumeEntity;
//import com.resume.Backend.parsing.JobMatchingService;
import com.resume.Backend.parsing.ExtractionService;
import com.resume.Backend.parsing.ParsingService;
//import com.resume.Backend.repository.JobRepository;
import com.resume.Backend.repository.ResumeRepo;
import com.resume.Backend.storingindb.FileStoringService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Set;


@Service
public class ResumeService {

    private final ResumeRepo repo;
    private final ParsingService parser;
    private final FileStoringService fileStoring;
    private final ExtractionService extractionService;
    private final JobRecommendationService jobRecommendationService;


    public ResumeService(ResumeRepo repo, ParsingService parser, FileStoringService fileStoring,
                         ExtractionService extractionService, JobRecommendationService jobRecommendationService) {
        this.repo = repo;
        this.parser = parser;
        this.fileStoring = fileStoring;
        this.extractionService = extractionService;
        this.jobRecommendationService = jobRecommendationService;
    }

    public ResumeAnalysisDto uploadResume(MultipartFile file) {

        String fileName = file.getOriginalFilename();

        isResumeValid(file);

        String newFileName = fileStoring.createUniqueFileName(fileName);

        File destination = fileStoring.storeFileInServerFile(newFileName, file);

        String parsedText = parser.extractStringFromFile(destination);


        ResumeDataDto resumeData = extractionService.createDTO(parsedText);

        Set<String> skills = extractionService.extractSkills(parsedText);

        ResumeAnalysisDto result = jobRecommendationService.analyzeResume(skills);

//        List<JobRecommendationDto> jobs = jobRecommendationService.getJobRecommendation(skills);

        // Saving the resume data in database
        saveInDB(resumeData);

        return result;
    }

    public void isResumeValid(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        long size = file.getSize();

        double fileSize = (double) size / (1024 * 1024);

        if (fileSize >= 5) {
            throw new InvalidFileException("File size too large. Up to 5mb size file is allowed.");
        }

        if (file.isEmpty()) {
            throw new InvalidFileException("File is Empty, again upload resume file with content");
        }

        assert fileName != null;
        if (fileName.isEmpty()) {
            throw new InvalidFileException("No File Name, upload a proper file with name");
        }

    }


    public ResumeDataDto saveInDB(ResumeDataDto resumeData) {
        ResumeEntity resumeEntity = convertToResumeEntity(resumeData);
        repo.save(resumeEntity);
        return resumeData;
    }

    public ResumeEntity convertToResumeEntity(ResumeDataDto resumeData) {
        ResumeEntity resumeEntity = new ResumeEntity();

        resumeEntity.setEmail(resumeData.getEmail());
        resumeEntity.setPhoneNo(resumeData.getPhoneNo());
        resumeEntity.setSkills(resumeData.getSkills());

        return resumeEntity;
    }

}
