package com.resumeats.repository;

import com.resumeats.model.ResumeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeProfile, Long> {
}