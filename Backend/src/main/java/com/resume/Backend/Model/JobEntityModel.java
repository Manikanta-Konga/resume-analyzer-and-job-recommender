package com.resume.Backend.model;

import jakarta.persistence.*;
import lombok.*;



@Data
@Entity
public class JobEntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String companyName;

    @Column(columnDefinition = "TEXT")
    private String requiredSkills;

    public JobEntityModel() {

    }

    public JobEntityModel(Long id, String jobTitle, String companyName, String requiredSkills) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.requiredSkills = requiredSkills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}
