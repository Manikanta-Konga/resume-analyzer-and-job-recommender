package com.resume.Backend.parsing;

import com.resume.Backend.dto.ResumeDataDto;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ExtractionService {

    public String extractPhoneNo(String text) {

        String regEx = "(\\+91[\\s-]?|0)?[6-9]\\d{9}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(text);
        String phoneNo = "";

        while(matcher.find()) {
            phoneNo = matcher.group();
            break;
        }

        return phoneNo;
    }

    public String extractEmail(String text) {
        String regEx = "[\\w-]+@\\w+\\.com";
        String email = "";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()) {
            email = matcher.group();
            break;
        }

        return email;
    }

    public Set<String> extractSkills(String text) {

        List<String> singleSkillSet = Arrays.asList(
                "java", "python", "javascript", "typescript", "html", "css", "react", "redux", "axios",
                "bootstrap", "mongodb", "mysql", "postgresql", "sql", "hibernate", "jpa", "jdbc",
                "maven", "jwt", "git", "github", "docker", "kubernetes", "node", "express",
                "mongoose", "django", "flask", "fastapi", "sqlalchemy", "pytest", "kotlin",
                "android", "firebase", "retrofit", "flutter", "dart", "provider", "bloc",
                "sqlite", "selenium", "testng", "junit", "postman", "jira", "excel",
                "pandas", "numpy", "tableau", "tensorflow", "pytorch", "linux", "aws",
                "azure", "gcp", "jenkins", "terraform", "ansible", "nginx", "wireshark",
                "nmap", "owasp", "siem", "firewalls", "agile", "communication",
                "networking", "statistics", "security", "monitoring", "microservices",
                "responsive", "algorithms", "oops", "dbms"
        );

        List<String> multiSkillSet = Arrays.asList(
                "spring boot", "spring mvc", "spring security", "react js", "tailwind css",
                "responsive design", "rest api", "rest api integration", "context api",
                "node js", "express js", "manual testing", "automation testing",
                "api testing", "rest assured", "test cases", "bug tracking",
                "machine learning", "deep learning", "data visualization",
                "feature engineering", "model deployment", "data cleaning",
                "data analysis", "cloud security", "shell scripting",
                "github actions", "ethical hacking", "penetration testing",
                "network security", "incident response", "business analysis",
                "requirement gathering", "stakeholder management", "problem solving",
                "computer networks", "operating systems", "state management",
                "responsive ui", "android studio", "jetpack compose",
                "version control", "object oriented programming",
                "database management system", "continuous integration",
                "continuous deployment", "software engineering",
                "cloud computing", "mobile app development",
                "frontend development", "backend development",
                "full stack development", "unit testing", "system design"
        );

        Set<String> wordSet = new HashSet<>();

        Set<String> extractedSkills = new HashSet<>();

        Pattern pattern = Pattern.compile("[a-z0-9#+.-]+");

        Matcher matcher = pattern.matcher(text);

        // Converting entire text into set of words
        while(matcher.find()) {
            wordSet.add(matcher.group());
        }

        // Matching multiskills in extracted wordset
        for(String skill : multiSkillSet) {
            // Pattern.quote(str) makes str as normal string and ignore regex rules(ex. c++, node.js)
            // \b is used for word breaking
            // .* zero or more characters before or after skill
            if(text.matches(".*\\b"+Pattern.quote(skill)+"\\b.*")) {
                extractedSkills.add(skill);
            }
        }

        for(String skill : singleSkillSet) {
            if(wordSet.contains(skill)) {
                boolean isPresentInBiggerPhrase = false;
                // We are checking whether the skill is part of extracted skill or not
                for(String word : extractedSkills) {
                    if(word.contains(" "+skill+" ") || word.startsWith(skill+" ")
                            || word.endsWith(" "+skill)) {
                        isPresentInBiggerPhrase = true;
                        break;
                    }
                }
                // If it is not a part of multi skill, then we are adding it to skill set.
                if(!isPresentInBiggerPhrase) {
                    extractedSkills.add(skill);
                }
            }
        }

        return normalizeSetSkills(extractedSkills);
    }

    public Set<String> normalizeSetSkills(Set<String> resumeSkillSet) {

        return resumeSkillSet
                .stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(skill -> !skill.isBlank())
                .collect(Collectors.toSet());
    }

    public ResumeDataDto createDTO(String text) {
        String email = extractEmail(text);
        String phoneNo = extractPhoneNo(text);
        Set<String> skills = extractSkills(text);

        return mapToDTO(phoneNo, email, skills);
    }

    public ResumeDataDto mapToDTO(String phoneNo, String email, Set<String> skills) {
        ResumeDataDto resumeData = new ResumeDataDto();

        resumeData.setEmail(email);
        resumeData.setPhoneNo(phoneNo);
        resumeData.setSkills(skills);

        return resumeData;
    }


//    public Float findMatchedSkillPercentage(Set<String> matchedSkills, Set<String> jobSkills) {
//
//        float totalRequiredSkillsCount = jobSkills.size();
//        int matchedSkillCount = matchedSkills.size();
//        float matchingPercentage = matchedSkillCount / totalRequiredSkillsCount * 100;
//
//        // Limiting decimal points to only 2.
//        matchingPercentage = Math.round(matchingPercentage * 100f) / 100.0f;
//
//        return matchingPercentage;
//    }


}
