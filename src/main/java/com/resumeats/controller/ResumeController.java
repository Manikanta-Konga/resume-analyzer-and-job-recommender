package com.resumeats.controller;

import com.resumeats.model.ResumeProfile;
import com.resumeats.repository.ResumeRepository;
import com.resumeats.service.JobRecommendationService;
import com.resumeats.service.ResumeExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeExtractionService extractionService;

    @Autowired
    private JobRecommendationService jobService;

    // THIS WAS MISSING: Injecting the database repository
    @Autowired
    private ResumeRepository resumeRepository;

    @PostMapping("/analyze")
    public ResponseEntity<Map<String, Object>> analyzeResume(@RequestParam("file") MultipartFile file) {
        try {
            // 1. Extract Text using Apache Tika
            String rawText = extractionService.extractTextFromFile(file);

            // 2. REAL Keyword Matching Algorithm
            String[] targetSkills = {"Java", "Spring Boot", "MySQL", "Python", "React", "Angular", "HTML", "CSS", "JavaScript", "SQL", "Docker", "AWS", "Git", "C++", "Data Science"};
            List<String> detectedSkillsList = new ArrayList<>();
            String textToSearch = rawText.toLowerCase();

            for (String skill : targetSkills) {
                if (textToSearch.contains(skill.toLowerCase())) {
                    detectedSkillsList.add(skill);
                }
            }

            String actualExtractedSkills = detectedSkillsList.isEmpty() ? "General" : String.join(", ", detectedSkillsList);

            // Calculate a basic ATS Score (e.g., 10 points per skill found, max 100)
            int actualAtsScore = Math.min(100, detectedSkillsList.size() * 10);

            // 3. Fetch Real Jobs from Adzuna based on the top skills (so we actually get results!)
            String searchQuery = "Software Engineer"; // Default fallback
            if (!detectedSkillsList.isEmpty()) {
                // Grab just the first 1 or 2 skills to make a broad job search
                int limit = Math.min(2, detectedSkillsList.size());
                searchQuery = String.join(" ", detectedSkillsList.subList(0, limit));
            }
            String jobsJson = jobService.getJobRecommendations(searchQuery);
            // 4. Save to MySQL Database
            ResumeProfile profile = new ResumeProfile();
            profile.setFileName(file.getOriginalFilename());
            profile.setExtractedSkills(actualExtractedSkills);
            profile.setAtsScore(actualAtsScore);
            resumeRepository.save(profile);

            // 5. Construct Response
            Map<String, Object> response = new HashMap<>();
            response.put("ats_score", actualAtsScore);
            response.put("skills_detected", actualExtractedSkills);
            response.put("job_recommendations", jobsJson);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // NEW ENDPOINT: Fetch all saved profiles for the Dashboard
    @GetMapping("/profiles")
    public ResponseEntity<List<ResumeProfile>> getAllProfiles() {
        try {
            // This single line asks MySQL for every row in the table!
            List<ResumeProfile> profiles = resumeRepository.findAll();
            return ResponseEntity.ok(profiles);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}