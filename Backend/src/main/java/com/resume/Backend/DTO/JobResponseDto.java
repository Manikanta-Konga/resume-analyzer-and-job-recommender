package com.resume.Backend.dto;


import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class JobResponseDto {

    private String jobTitle;
    private String companyName;
    private Set<String> matchedSkills;
    private Set<String> missingSkills;
    private float matchPercentage;

    public JobResponseDto() {

    }

    public JobResponseDto(String jobTitle, String companyName, Set<String> matchedSkills,
                          Set<String> missingSkills, float matchPercentage) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.matchPercentage = matchPercentage;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(Set<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public Set<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(Set<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public float getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(float matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
}
