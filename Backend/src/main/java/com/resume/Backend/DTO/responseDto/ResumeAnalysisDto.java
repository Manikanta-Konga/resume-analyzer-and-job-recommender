package com.resume.Backend.dto.responseDto;

import com.resume.Backend.dto.JobRecommendationDto;

import java.util.List;

public class ResumeAnalysisDto {

    private String predictedRole;

    private double atsScore;

    private List<String> matchedSkills;

    private List<String> missingSkills;

    private List<JobRecommendationDto> jobRecommendations;

    public ResumeAnalysisDto() {
    }

    public ResumeAnalysisDto(
            String predictedRole,
            double atsScore,
            List<String> matchedSkills,
            List<String> missingSkills,
            List<JobRecommendationDto> jobRecommendations
    ) {
        this.predictedRole = predictedRole;
        this.atsScore = atsScore;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.jobRecommendations = jobRecommendations;
    }

    public String getPredictedRole() {
        return predictedRole;
    }

    public void setPredictedRole(String predictedRole) {
        this.predictedRole = predictedRole;
    }

    public double getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(double atsScore) {
        this.atsScore = atsScore;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public List<JobRecommendationDto> getJobRecommendations() {
        return jobRecommendations;
    }

    public void setJobRecommendations(List<JobRecommendationDto> jobRecommendations) {
        this.jobRecommendations = jobRecommendations;
    }
}

