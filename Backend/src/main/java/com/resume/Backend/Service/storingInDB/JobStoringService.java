package com.resume.Backend.service.storingInDB;

import com.resume.Backend.DTO.JobRequestDTO;
import com.resume.Backend.model.JobEntityModel;
import com.resume.Backend.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobStoringService {

    private final JobRepository jobRepo;

    public JobStoringService(JobRepository jobRepo) {
        this.jobRepo = jobRepo;
    }

    public JobRequestDTO saveJobInDB(JobRequestDTO jobRequestDTO) {

        Set<String> cleanedSkills = jobRequestDTO.getRequiredSkills()
                .stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        String skillSet = String.join(",", cleanedSkills);

        JobEntityModel jobEntity = new JobEntityModel();

        jobEntity.setJobTitle(jobRequestDTO.getJobTitle());
        jobEntity.setCompanyName(jobRequestDTO.getCompanyName());
        jobEntity.setRequiredSkills(skillSet);

        jobRepo.save(jobEntity);

        return jobRequestDTO;
    }

}
