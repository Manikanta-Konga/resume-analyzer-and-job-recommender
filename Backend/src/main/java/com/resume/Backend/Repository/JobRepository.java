package com.resume.Backend.repository;

import com.resume.Backend.model.JobEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobEntityModel, Long> {



}
