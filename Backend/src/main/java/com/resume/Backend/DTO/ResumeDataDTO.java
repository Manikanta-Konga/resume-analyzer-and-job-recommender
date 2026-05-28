package com.resume.Backend.dto;

import lombok.*;

import java.util.Set;


@Data
public class ResumeDataDto {

    private String email;
    private String phoneNo;
    private Set<String> skills;

    public ResumeDataDto() {}


    public ResumeDataDto(String email, String phoneNo, Set<String> skills) {
        this.email = email;
        this.phoneNo = phoneNo;
        this.skills = skills;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
