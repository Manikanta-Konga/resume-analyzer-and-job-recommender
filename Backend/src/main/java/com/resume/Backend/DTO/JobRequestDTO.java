package com.resume.Backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Component
@Data
public class JobRequestDTO {

    @NotBlank(message = "Job title shouldn't be empty")
    private String jobTitle;

    @NotBlank(message = "Company name shouldn't be blank")
    private String companyName;

    @NotEmpty(message = "Atleast one required skill is enough")
    private Set<String> requiredSkills;

    public JobRequestDTO() {

    }

    public JobRequestDTO(String jobTitle, String companyName, Set<String> requiredSkills) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.requiredSkills = requiredSkills;
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

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}
