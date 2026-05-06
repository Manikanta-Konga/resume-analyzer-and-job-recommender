package com.resume.Backend.repository;

import com.resume.Backend.model.JobDescriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDescriptionRepo extends JpaRepository<JobDescriptionModel, Long> {


}
