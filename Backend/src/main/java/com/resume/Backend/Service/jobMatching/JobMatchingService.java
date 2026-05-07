package com.resume.Backend.service.jobMatching;

import com.resume.Backend.DTO.ResponseDTO;
import com.resume.Backend.model.JobEntityModel;
import com.resume.Backend.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobMatchingService {

    private final JobRepository jobRepo;

    public JobMatchingService(JobRepository jobRepo) {
        this.jobRepo = jobRepo;
    }

    public List<ResponseDTO> matchTheJobs(Set<String> resumeSkillSet) {

        List<JobEntityModel> jobList = jobRepo.findAll();
        List<ResponseDTO> matchedJobList = new ArrayList<>();
        Set<String> resumeSkills = normalizeSetSkills(resumeSkillSet);

        for(JobEntityModel job : jobList) {

            String requiredSkillsString = job.getRequiredSkills();
            Set<String> matchedSkills = new HashSet<>();
            Set<String> missingSkills = new HashSet<>();

            if (requiredSkillsString == null || requiredSkillsString.isBlank()) {
                continue;
            }

            String[]  jobSkillsInString = requiredSkillsString.split(",");
            Set<String> jobSkills = normalizeStringSkills(jobSkillsInString);

            if(jobSkills.isEmpty()) {
                continue;
            }

            for(String skill : jobSkills) {
                if(resumeSkills.contains(skill)) {
                    matchedSkills.add(skill);
                } else {
                    missingSkills.add(skill);
                }
            }

            float matchingPercentage = findMatchedSkillPercentage(matchedSkills, jobSkills);

            ResponseDTO matchedJob = buildResponseDTO(job.getJobTitle(), job.getCompanyName(),
                    matchedSkills, missingSkills, matchingPercentage);

            matchedJobList.add(matchedJob);

        }

        //ordering recommended jobs in descending order
        matchedJobList.sort((a, b) ->
                Float.compare(b.getMatchPercentage(), a.getMatchPercentage()));

        return matchedJobList;
    }

    public Set<String> normalizeStringSkills(String[] jobSkillArr) {

        return Arrays.stream(jobSkillArr)
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(skill -> !skill.isBlank())
                .collect(Collectors.toSet());
    }


    public Set<String> normalizeSetSkills(Set<String> resumeSkillSet) {

        return resumeSkillSet
                .stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(skill -> !skill.isBlank())
                .collect(Collectors.toSet());
    }


    public Float findMatchedSkillPercentage(Set<String> matchedSkills, Set<String> jobSkills) {

        float totalRequiredSkillsCount = jobSkills.size();
        int matchedSkillCount = matchedSkills.size();
        float matchingPercentage = matchedSkillCount / totalRequiredSkillsCount * 100;

        // Limiting decimal points to only 2.
        matchingPercentage = Math.round(matchingPercentage * 100f) / 100.0f;

        return matchingPercentage;
    }



    public ResponseDTO buildResponseDTO(String jobTitle, String companyName,Set<String> matchedSkills,
                                        Set<String> missingSkills, float matchPercentage) {

        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setJobTitle(jobTitle);
        responseDTO.setCompanyName(companyName);
        responseDTO.setMatchedSkills(matchedSkills);
        responseDTO.setMissingSkills(missingSkills);
        responseDTO.setMatchPercentage(matchPercentage);

        return responseDTO;
    }

}
