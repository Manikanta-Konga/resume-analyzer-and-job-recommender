//package com.resume.Backend.parsing;
//
//import com.resume.Backend.dto.ResponseDTO;
//import com.resume.Backend.entity.JobEntity;
//import com.resume.Backend.repository.JobRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class JobMatchingService {
//
//    private final JobRepository jobRepo;
//
//    public JobMatchingService(JobRepository jobRepo) {
//        this.jobRepo = jobRepo;
//    }
//
//    public List<ResponseDTO> matchTheJobs(Set<String> resumeSkillSet) {
//
//        List<JobEntity> jobList = jobRepo.findAll();
//        List<ResponseDTO> matchedJobList = new ArrayList<>();
//        Set<String> resumeSkills = normalizeSetSkills(resumeSkillSet);
//
//       System.out.println("normal");
//
//        for(JobEntity job : jobList) {
//
//            String requiredSkillsString = job.getRequiredSkills();
//            Set<String> matchedSkills = new HashSet<>();
//            Set<String> missingSkills = new HashSet<>();
//
//            if (requiredSkillsString == null || requiredSkillsString.isBlank()) {
//                continue;
//            }
//
//            String[]  jobSkillsInString = requiredSkillsString.split(",");
//            Set<String> jobSkills = normalizeStringSkills(jobSkillsInString);
//
//            if(jobSkills.isEmpty()) {
//                continue;
//            }
//
//            for(String skill : jobSkills) {
//                if(resumeSkills.contains(skill)) {
//                    matchedSkills.add(skill);
//                } else {
//                    missingSkills.add(skill);
//                }
//            }
//
//            float matchingPercentage = findMatchedSkillPercentage(matchedSkills, jobSkills);
//
//            ResponseDTO matchedJob = buildResponseDTO(job.getJobTitle(), job.getCompanyName(),
//                    matchedSkills, missingSkills, matchingPercentage);
//
//            System.out.println("MatchedJob: ");
//            System.out.println(matchedJob);
//
//            matchedJobList.add(matchedJob);
//
//
//        }
//
//        System.out.println("matchedjoblist-------1");
//
//        System.out.println(matchedJobList);
//        //ordering recommended jobs in descending order
//        matchedJobList.sort((a, b) ->
//                Float.compare(b.getMatchPercentage(), a.getMatchPercentage()));
//
//        System.out.println("matchedjoblist-------1");
//        System.out.println(matchedJobList);
//
//
//        return matchedJobList;
//    }
//
//
//
//    public Set<String> normalizeStringSkills(String[] jobSkillArr) {
//
//        return Arrays.stream(jobSkillArr)
//                .map(String::trim)
//                .map(String::toLowerCase)
//                .filter(skill -> !skill.isBlank())
//                .collect(Collectors.toSet());
//    }
//
//
//
//
//
//
//
//
//    public ResponseDTO buildResponseDTO(String jobTitle, String companyName,Set<String> matchedSkills,
//                                        Set<String> missingSkills, float matchPercentage) {
//
//        ResponseDTO responseDTO = new ResponseDTO();
//
//        responseDTO.setJobTitle(jobTitle);
//        responseDTO.setCompanyName(companyName);
//        responseDTO.setMatchedSkills(matchedSkills);
//        responseDTO.setMissingSkills(missingSkills);
//        responseDTO.setMatchPercentage(matchPercentage);
//
//        return responseDTO;
//    }
//
//}
