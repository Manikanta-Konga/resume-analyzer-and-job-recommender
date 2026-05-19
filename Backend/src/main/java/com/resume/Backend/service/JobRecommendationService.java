package com.resume.Backend.service;


import com.resume.Backend.dto.AdzunaResponseDto;
import com.resume.Backend.dto.JobRecommendationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobRecommendationService {


//    @Value("${adzuna.app.id}")
    private String appId = "79eeb71b";

//    @Value("${adzuna.app.key}")
    private String appKey = "3d763a0c66495c6c0d3ab7ebb8fd3787";

    public List<JobRecommendationDto> getJobRecommendation(Set<String> skills) {
        String searchQuery = String.join(" OR ", skills);
        String role = predictRole(skills).replace(" ", "_");
        String apiUrl = UriComponentsBuilder
                .fromUriString("https://api.adzuna.com/v1/api/jobs/in/search/1")
                .queryParam("app_id", appId)
                .queryParam("app_key", appKey)
                .queryParam("what", role)
                .build()
                .toUriString();

        System.out.println();
        System.out.println(apiUrl);
        System.out.println();

        RestTemplate restTemplate = new RestTemplate();

        AdzunaResponseDto adzunaResponseDto = restTemplate.getForObject(apiUrl, AdzunaResponseDto.class);

        return convertToDto(adzunaResponseDto);
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

    public String predictRole(Set<String> userSkills) {

        Map<String, Map<String, Integer>> roleSkills = Map.of(

                "Java Backend Developer",
                Map.of(
                        "java", 5,
                        "spring boot", 5,
                        "hibernate", 4,
                        "jpa", 4,
                        "mysql", 3,
                        "postgresql", 3,
                        "rest api", 4,
                        "microservices", 4,
                        "docker", 2,
                        "git hub", 2
                ),

                "Frontend Developer",
                Map.of(
                        "react", 5,
                        "javascript", 5,
                        "typescript", 4,
                        "html", 4,
                        "css", 4,
                        "bootstrap", 3,
                        "tailwind css", 3,
                        "redux", 3,
                        "angular", 4
                ),

                "Full Stack Developer",
                Map.of(
                        "java", 5,
                        "spring boot", 5,
                        "react", 5,
                        "javascript", 4,
                        "html", 3,
                        "css", 3,
                        "mysql", 3,
                        "rest api", 4,
                        "git hub", 2
                ),

                "Python Developer",
                Map.of(
                        "python", 5,
                        "django", 5,
                        "flask", 4,
                        "fastapi", 4,
                        "postgresql", 3,
                        "rest api", 3,
                        "pandas", 2,
                        "numpy", 2
                ),

                "Data Scientist",
                Map.of(
                        "python", 5,
                        "machine learning", 5,
                        "deep learning", 4,
                        "tensorflow", 4,
                        "pytorch", 4,
                        "pandas", 4,
                        "numpy", 4,
                        "sql", 3,
                        "data analysis", 4
                ),

                "DevOps Engineer",
                Map.of(
                        "docker", 5,
                        "kubernetes", 5,
                        "aws", 5,
                        "jenkins", 4,
                        "ci/cd", 4,
                        "linux", 4,
                        "terraform", 4,
                        "ansible", 3
                ),

                "Android Developer",
                Map.of(
                        "java", 4,
                        "kotlin", 5,
                        "android", 5,
                        "firebase", 4,
                        "xml", 3,
                        "jetpack compose", 4
                ),

                "Flutter Developer",
                Map.of(
                        "flutter", 5,
                        "dart", 5,
                        "firebase", 4,
                        "rest api", 3,
                        "provider", 3,
                        "bloc", 3
                ),

                "Cloud Engineer",
                Map.of(
                        "aws", 5,
                        "azure", 5,
                        "gcp", 5,
                        "docker", 4,
                        "kubernetes", 4,
                        "linux", 3,
                        "terraform", 4
                ),

                "QA Engineer",
                Map.of(
                        "selenium", 5,
                        "junit", 4,
                        "testng", 4,
                        "automation testing", 5,
                        "api testing", 4,
                        "postman", 4
                )
        );

        String bestRole = "Software Developer";
        int maxScore = 0;

        for (Map.Entry<String, Map<String, Integer>> entry : roleSkills.entrySet()) {

            int score = 0;

            for (Map.Entry<String, Integer> skillEntry : entry.getValue().entrySet()) {

                String requiredSkill = skillEntry.getKey();
                int weight = skillEntry.getValue();

                if (userSkills.contains(requiredSkill)) {
                    score += weight;
                }
            }

            if (score > maxScore) {
                maxScore = score;
                bestRole = entry.getKey();
            }
        }

        return bestRole;
    }

}
