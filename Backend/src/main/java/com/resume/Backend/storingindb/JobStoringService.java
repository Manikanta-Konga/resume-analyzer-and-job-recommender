//package com.resume.Backend.storingindb;
//
//import com.resume.Backend.dto.JobRequestDTO;
//import com.resume.Backend.entity.JobEntity;
//import com.resume.Backend.repository.JobRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class JobStoringService {
//
//    private final JobRepository jobRepo;
//
//    public JobStoringService(JobRepository jobRepo) {
//        this.jobRepo = jobRepo;
//    }
//
//    public JobRequestDTO saveJobInDB(JobRequestDTO jobRequestDTO) {
//
//        Set<String> cleanedSkills = jobRequestDTO.getRequiredSkills()
//                .stream()
//                .map(String::trim)
//                .map(String::toLowerCase)
//                .collect(Collectors.toSet());
//
//        String skillSet = String.join(",", cleanedSkills);
//
//        JobEntity jobEntity = new JobEntity();
//
//        jobEntity.setJobTitle(jobRequestDTO.getJobTitle());
//        jobEntity.setCompanyName(jobRequestDTO.getCompanyName());
//        jobEntity.setRequiredSkills(skillSet);
//
//        jobRepo.save(jobEntity);
//
//        return jobRequestDTO;
//    }
//
//}
