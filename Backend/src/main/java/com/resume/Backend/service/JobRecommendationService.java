package com.resume.Backend.service;


import com.resume.Backend.dto.responseDto.AdzunaResponseDto;
import com.resume.Backend.dto.responseDto.ResumeAnalysisDto;
import com.resume.Backend.dto.responseDto.RoleAnalysisResult;
import com.resume.Backend.dto.JobRecommendationDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class JobRecommendationService {


    @Value("${adzuna.api.id}")
    private String appId;

    @Value("${adzuna.api.key}")
    private String appKey;

    private final Map<String, Map<String, Integer>> roleSkills = Map.ofEntries(

            Map.entry(
            "Java Backend Developer",
            Map.ofEntries(
            Map.entry("java", 5),
            Map.entry("spring boot", 5),
            Map.entry("spring mvc", 4),
            Map.entry("spring security", 4),
            Map.entry("hibernate", 4),
            Map.entry("jpa", 4),
            Map.entry("jdbc", 4),
            Map.entry("sql", 5),
            Map.entry("mysql", 4),
            Map.entry("postgresql", 4),
            Map.entry("rest api", 5),
            Map.entry("microservices", 4),
            Map.entry("maven", 4),
            Map.entry("jwt", 4),
            Map.entry("git", 4),
            Map.entry("github", 4),
            Map.entry("junit", 3),
            Map.entry("mockito", 3),
            Map.entry("docker", 3)
            )
            ),

            Map.entry(
            "Frontend Developer",
            Map.ofEntries(
            Map.entry("html", 5),
            Map.entry("css", 5),
            Map.entry("javascript", 5),
            Map.entry("typescript", 4),
            Map.entry("react", 5),
            Map.entry("react js", 5),
            Map.entry("redux", 4),
            Map.entry("tailwind css", 4),
            Map.entry("bootstrap", 4),
            Map.entry("responsive design", 5),
            Map.entry("rest api integration", 4),
            Map.entry("axios", 4),
            Map.entry("git", 4),
            Map.entry("github", 4),
            Map.entry("npm", 3)
            )
            ),

            Map.entry(
            "React Developer",
            Map.ofEntries(
            Map.entry("react", 5),
            Map.entry("react js", 5),
            Map.entry("javascript", 5),
            Map.entry("typescript", 4),
            Map.entry("html", 5),
            Map.entry("css", 5),
            Map.entry("redux", 4),
            Map.entry("context api", 3),
            Map.entry("tailwind css", 4),
            Map.entry("bootstrap", 3),
            Map.entry("axios", 4),
            Map.entry("rest api", 4),
            Map.entry("responsive design", 5),
            Map.entry("git", 4),
            Map.entry("github", 4)
            )
            ),

            Map.entry(
            "Java Full Stack Developer",
            Map.ofEntries(
            Map.entry("java", 5),
            Map.entry("spring boot", 5),
            Map.entry("spring security", 4),
            Map.entry("hibernate", 4),
            Map.entry("jpa", 4),
            Map.entry("sql", 5),
            Map.entry("mysql", 4),
            Map.entry("rest api", 5),
            Map.entry("html", 5),
            Map.entry("css", 5),
            Map.entry("javascript", 5),
            Map.entry("react", 5),
            Map.entry("react js", 5),
            Map.entry("redux", 3),
            Map.entry("tailwind css", 3),
            Map.entry("jwt", 4),
            Map.entry("git", 4),
            Map.entry("github", 4),
            Map.entry("docker", 3)
            )
            ),

            Map.entry(
            "MERN Stack Developer",
            Map.ofEntries(
            Map.entry("mongodb", 5),
            Map.entry("express js", 5),
            Map.entry("react", 5),
            Map.entry("node js", 5),
            Map.entry("javascript", 5),
            Map.entry("typescript", 4),
            Map.entry("html", 4),
            Map.entry("css", 4),
            Map.entry("redux", 3),
            Map.entry("rest api", 5),
            Map.entry("jwt", 4),
            Map.entry("mongoose", 4),
            Map.entry("tailwind css", 3),
            Map.entry("git", 4),
            Map.entry("github", 4),
            Map.entry("docker", 3)
            )
            ),

            Map.entry(
            "Node JS Developer",
            Map.ofEntries(
            Map.entry("node js", 5),
            Map.entry("express js", 5),
            Map.entry("javascript", 5),
            Map.entry("typescript", 4),
            Map.entry("mongodb", 4),
            Map.entry("mongoose", 4),
            Map.entry("sql", 4),
            Map.entry("mysql", 3),
            Map.entry("postgresql", 3),
            Map.entry("rest api", 5),
            Map.entry("jwt", 4),
            Map.entry("git", 4),
            Map.entry("github", 4),
            Map.entry("docker", 3)
            )
            ),

            Map.entry(
            "Python Developer",
            Map.ofEntries(
            Map.entry("python", 5),
            Map.entry("django", 5),
            Map.entry("flask", 4),
            Map.entry("fastapi", 4),
            Map.entry("sql", 5),
            Map.entry("postgresql", 4),
            Map.entry("mysql", 3),
            Map.entry("rest api", 5),
            Map.entry("sqlalchemy", 3),
            Map.entry("jwt", 3),
            Map.entry("git", 4),
            Map.entry("github", 4),
            Map.entry("docker", 3),
            Map.entry("pytest", 3)
            )
            ),

            Map.entry(
            "Android Developer",
            Map.ofEntries(
            Map.entry("kotlin", 5),
            Map.entry("java", 4),
            Map.entry("android", 5),
            Map.entry("android studio", 5),
            Map.entry("xml", 4),
            Map.entry("firebase", 4),
            Map.entry("jetpack compose", 4),
            Map.entry("rest api", 4),
            Map.entry("retrofit", 4),
            Map.entry("mvvm", 4),
            Map.entry("git", 4),
            Map.entry("github", 4)
            )
            ),

            Map.entry(
            "Flutter Developer",
            Map.ofEntries(
            Map.entry("flutter", 5),
            Map.entry("dart", 5),
            Map.entry("firebase", 4),
            Map.entry("rest api", 4),
            Map.entry("provider", 4),
            Map.entry("bloc", 4),
            Map.entry("state management", 5),
            Map.entry("responsive ui", 4),
            Map.entry("sqlite", 3),
            Map.entry("git", 4),
            Map.entry("github", 4)
            )
            ),

            Map.entry(
            "Software Engineer",
            Map.ofEntries(
            Map.entry("data structures", 5),
            Map.entry("algorithms", 5),
            Map.entry("oops", 5),
            Map.entry("dbms", 4),
            Map.entry("operating systems", 4),
            Map.entry("computer networks", 4),
            Map.entry("sql", 4),
            Map.entry("problem solving", 5),
            Map.entry("git", 4),
            Map.entry("github", 4)
            )
            ),

            Map.entry(
            "Software Test Engineer",
            Map.ofEntries(
            Map.entry("manual testing", 5),
            Map.entry("automation testing", 5),
            Map.entry("selenium", 5),
            Map.entry("testng", 4),
            Map.entry("junit", 4),
            Map.entry("api testing", 5),
            Map.entry("postman", 5),
            Map.entry("rest assured", 4),
            Map.entry("jira", 4),
            Map.entry("test cases", 5),
            Map.entry("bug tracking", 4),
            Map.entry("sql", 3)
            )
            ),

            Map.entry(
            "QA Engineer",
            Map.ofEntries(
            Map.entry("manual testing", 5),
            Map.entry("automation testing", 5),
            Map.entry("selenium", 5),
            Map.entry("testng", 4),
            Map.entry("api testing", 5),
            Map.entry("postman", 5),
            Map.entry("rest assured", 4),
            Map.entry("jira", 4),
            Map.entry("test cases", 5),
            Map.entry("bug tracking", 4),
            Map.entry("sql", 3)
            )
            ),

            Map.entry(
            "Data Analyst",
            Map.ofEntries(
            Map.entry("sql", 5),
            Map.entry("excel", 5),
            Map.entry("power bi", 5),
            Map.entry("tableau", 4),
            Map.entry("python", 4),
            Map.entry("pandas", 4),
            Map.entry("data cleaning", 4),
            Map.entry("data visualization", 5),
            Map.entry("statistics", 4)
            )
            ),

            Map.entry(
            "Data Scientist",
            Map.ofEntries(
            Map.entry("python", 5),
            Map.entry("machine learning", 5),
            Map.entry("deep learning", 4),
            Map.entry("pandas", 5),
            Map.entry("numpy", 5),
            Map.entry("scikit learn", 5),
            Map.entry("tensorflow", 4),
            Map.entry("pytorch", 4),
            Map.entry("sql", 4),
            Map.entry("statistics", 4),
            Map.entry("data visualization", 4),
            Map.entry("feature engineering", 3)
            )
            ),

            Map.entry(
            "Machine Learning Engineer",
            Map.ofEntries(
            Map.entry("python", 5),
            Map.entry("machine learning", 5),
            Map.entry("deep learning", 4),
            Map.entry("tensorflow", 4),
            Map.entry("pytorch", 4),
            Map.entry("scikit learn", 5),
            Map.entry("pandas", 4),
            Map.entry("numpy", 4),
            Map.entry("feature engineering", 4),
            Map.entry("model deployment", 3),
            Map.entry("sql", 3)
            )
            ),

            Map.entry(
            "DevOps Engineer",
            Map.ofEntries(
            Map.entry("linux", 5),
            Map.entry("docker", 5),
            Map.entry("kubernetes", 5),
            Map.entry("aws", 5),
            Map.entry("jenkins", 5),
            Map.entry("ci/cd", 5),
            Map.entry("terraform", 4),
            Map.entry("ansible", 4),
            Map.entry("shell scripting", 4),
            Map.entry("git", 4),
            Map.entry("github actions", 4),
            Map.entry("nginx", 3)
            )
            ),

            Map.entry(
            "Cloud Engineer",
            Map.ofEntries(
            Map.entry("aws", 5),
            Map.entry("azure", 4),
            Map.entry("gcp", 4),
            Map.entry("linux", 5),
            Map.entry("docker", 4),
            Map.entry("kubernetes", 4),
            Map.entry("terraform", 4),
            Map.entry("networking", 4),
            Map.entry("cloud security", 4),
            Map.entry("iam", 4)
            )
            ),

            Map.entry(
            "Cyber Security Analyst",
            Map.ofEntries(
            Map.entry("cyber security", 5),
            Map.entry("network security", 5),
            Map.entry("ethical hacking", 4),
            Map.entry("penetration testing", 4),
            Map.entry("owasp", 4),
            Map.entry("linux", 4),
            Map.entry("wireshark", 3),
            Map.entry("nmap", 3),
            Map.entry("siem", 3),
            Map.entry("firewalls", 4),
            Map.entry("incident response", 3)
            )
            ),

            Map.entry(
            "Business Analyst",
            Map.ofEntries(
            Map.entry("business analysis", 5),
            Map.entry("requirement gathering", 5),
            Map.entry("excel", 5),
            Map.entry("sql", 4),
            Map.entry("power bi", 4),
            Map.entry("jira", 4),
            Map.entry("agile", 4),
            Map.entry("documentation", 4),
            Map.entry("communication", 5),
            Map.entry("stakeholder management", 4)
            )
            )

            );

    public ResumeAnalysisDto analyzeResume(Set<String> skills) {

        RoleAnalysisResult result = findBestMatchingRole(skills);

        List<JobRecommendationDto> jobs = getJobRecommendation(skills, result.getRoleName());

        ResumeAnalysisDto response = new ResumeAnalysisDto();

        response.setPredictedRole(result.getRoleName());
        response.setAtsScore(result.getAtsScore());
        response.setMatchedSkills(result.getMatchedSkills());
        response.setMissingSkills(result.getMissingSkills());
        response.setJobRecommendations(jobs);

        return response;
    }



    public List<JobRecommendationDto> getJobRecommendation(Set<String> skills, String role) {

        role = role.replace(" ", "_");
        String apiUrl = UriComponentsBuilder
                .fromUriString("https://api.adzuna.com/v1/api/jobs/in/search/1")
                .queryParam("app_id", appId)
                .queryParam("app_key", appKey)
                .queryParam("what", role)
                .build()
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        AdzunaResponseDto adzunaResponseDto = restTemplate.getForObject(apiUrl, AdzunaResponseDto.class);

        List<JobRecommendationDto> recommendedJobs = convertToDto(adzunaResponseDto);

        return recommendedJobs;
    }

    public List<JobRecommendationDto> convertToDto(AdzunaResponseDto jobs) {
        return jobs.getResults()
                .stream()
                .map(job -> {
                    JobRecommendationDto dto = new JobRecommendationDto();
                    dto.setTitle(job.getTitle());
                    dto.setCompany(
                            job.getCompany().getDisplay_name()
                    );
                    dto.setLocation(
                            job.getLocation().getDisplay_name()
                    );
                    dto.setDescription(job.getDescription());
                    dto.setApplyUrl(job.getRedirect_url());
                    return dto;
                })
                .toList();
    }

    public RoleAnalysisResult findBestMatchingRole(Set<String> userSkills) {

        String bestRole = "Software Developer";

        double bestScore = 0;
        List<String> finalMatchingSkills = new ArrayList<>();
        List<String> finalMissingSkills = new ArrayList<>();

        for (Map.Entry<String, Map<String, Integer>> entry : roleSkills.entrySet()) {

            int score = 0;

            List<String> matchedSkills = new ArrayList<>();
            List<String> missingSkills = new ArrayList<>();

            int matchedWeight = 0;
            int totalWeight = 0;

            for (Map.Entry<String, Integer> skillEntry : entry.getValue().entrySet()) {

                String requiredSkill = skillEntry.getKey();
                int weight = skillEntry.getValue();

                totalWeight += weight;

                if (userSkills.contains(requiredSkill)) {
                    matchedSkills.add(requiredSkill);
                    matchedWeight += weight;
                }else {
                    missingSkills.add(requiredSkill);
                }
            }

            double currentScore = (matchedWeight * 100.0) / totalWeight;
            if (currentScore > bestScore) {
                bestScore = (int)currentScore;
                bestRole = entry.getKey();
                finalMatchingSkills = matchedSkills;
                finalMissingSkills = missingSkills;
            }
        }

        return new RoleAnalysisResult(
                bestRole,
                bestScore,
                finalMatchingSkills,
                finalMissingSkills
        );
    }



}
