package com.resume.Backend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "resumes")
@Data
public class ResumeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "resume_skills",
            joinColumns = @JoinColumn(name = "resume_id")
    )

    @Column(name = "skill")
    private Set<String> skills = new HashSet<>();

    public ResumeEntity() {

    }

    public ResumeEntity(Long id, String email, String phoneNo, Set<String> skills) {
        this.id = id;
        this.email = email;
        this.phoneNo = phoneNo;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
