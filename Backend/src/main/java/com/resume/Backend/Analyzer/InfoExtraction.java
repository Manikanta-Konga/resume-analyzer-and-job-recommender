package com.resume.Backend.Analyzer;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class InfoExtraction {

//    public String formattingFileContent(String text) {
//
//    }

    public ArrayList<String> findEmail(String text) {
        String regEx = "[\\w-]+@\\w+\\.com";
        ArrayList<String> emails = new ArrayList<>();
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()) {
            emails.add(matcher.group());
        }

        return emails;
    }

    public Set<String> extractSkills(String text) {

        List<String> singleSkillSet = Arrays.asList( "java", "python", "c", "cpp", "javascript", "typescript",
                "html", "css", "react", "angular", "vue", "node", "spring", "django", "flask", "mysql", "postgresql",
                "mongodb", "oracle", "firebase", "redis", "git", "github", "docker", "kubernetes", "jenkins",
                "linux", "aws", "azure", "gcp", "dsa", "sql", "pandas", "numpy", "tableau", "excel", "tensorflow",
                "pytorch","security", "cryptography","flutter", "android", "ios","oop", "dbms", "os", "networks"
        );

        List<String> multiSkillSet = Arrays.asList(
                "core java", "advanced java", "spring boot", "machine learning", "deep learning","data science",
                "object oriented programming", "computer networks","operating systems", "database management system",
                "natural language processing", "computer vision","web development", "frontend development",
                "backend development", "full stack development", "mobile app development", "rest api",
                "restful services", "microservices architecture","version control", "agile methodology",
                "software engineering","cloud computing", "continuous integration", "continuous deployment",
                "test driven development", "unit testing", "integration testing","performance testing", "functional testing",
                "responsive design", "state management","authentication authorization", "role based access control",
                "exception handling", "dependency injection","design patterns", "system design", "data analysis", "data structures"
        );

        Set<String> wordSet = new HashSet<>();

        Set<String> extractedSkills = new HashSet<>();

        Pattern pattern = Pattern.compile("[a-z0-9#+.-]+");

        Matcher matcher = pattern.matcher(text);

        while(matcher.find()) {
            wordSet.add(matcher.group());
        }

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
                boolean isPresentIsBiggerPhrase = false;
                // We are checking whether the skill is part of extracted skill or not
                for(String word : extractedSkills) {
                    if(word.contains(" "+skill+" ") || word.startsWith(skill+" ")
                            || word.endsWith(" "+skill)) {
                        isPresentIsBiggerPhrase = true;
                        break;
                    }
                }
                // If it is not a part of multi skill, then we are adding it to skill set.
                if(!isPresentIsBiggerPhrase) {
                    extractedSkills.add(skill);
                }
            }
        }

        System.out.println(extractedSkills);

        return extractedSkills;
    }

    public ArrayList<String> findPhoneNo(String text) {

        String regEx = "(\\+91[\\s-]?|0)?[6-9]\\d{9}";

        Pattern pattern = Pattern.compile(regEx);

        Matcher matcher = pattern.matcher(text);

        ArrayList<String> list = new ArrayList<>();


        while(matcher.find()) {
            list.add(matcher.group());
        }

        return list;
    }

}
