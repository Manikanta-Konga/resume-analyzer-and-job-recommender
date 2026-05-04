package com.resume.Backend.Analyzer;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class InfoExtraction {

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

        List<String> singleSkillSet = Arrays.asList( "java", "dsa", "c", "html", "css", "javascript",
                                "mysql", "spring", "github", "git");

        List<String> multiSkillSet = Arrays.asList("spring boot", "machine learning", "artificial intelligence",
                "deep learning", "data science");

        Set<String> wordSet = new HashSet<>();

        Set<String> extractedSkills = new HashSet<>();

        Pattern pattern = Pattern.compile("[a-z0-9]+");

        Matcher matcher = pattern.matcher(text);

        while(matcher.find()) {
            wordSet.add(matcher.group());
        }

        for(String skill : multiSkillSet) {
            if(text.contains(skill)) {
                extractedSkills.add(skill);
            }
        }

        for(String skill : singleSkillSet) {
            if(wordSet.contains(skill)) {
                extractedSkills.add(skill);
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
