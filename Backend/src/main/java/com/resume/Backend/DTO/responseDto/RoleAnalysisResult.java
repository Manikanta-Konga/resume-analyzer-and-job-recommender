package com.resume.Backend.dto.responseDto;

import java.util.List;

public class RoleAnalysisResult {

    private String roleName;

    private double atsScore;

    private List<String> matchedSkills;

    private List<String> missingSkills;

    public RoleAnalysisResult() {
    }

    public RoleAnalysisResult(
            String roleName,
            double atsScore,
            List<String> matchedSkills,
            List<String> missingSkills
    ) {
        this.roleName = roleName;
        this.atsScore = atsScore;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
    }

    public String getRoleName() {
        return roleName;
    }

    public double getAtsScore() {
        return atsScore;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }
}

