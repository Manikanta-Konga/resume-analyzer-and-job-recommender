package com.resume.Backend.Repository;

import com.resume.Backend.Model.JobDescriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDescriptionRepo extends JpaRepository<JobDescriptionModel, Long> {


}
