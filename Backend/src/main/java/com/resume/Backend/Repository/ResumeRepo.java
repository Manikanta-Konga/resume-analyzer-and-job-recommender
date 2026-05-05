package com.resume.Backend.Repository;


import com.resume.Backend.Model.ResumeDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepo extends JpaRepository<ResumeDataModel, Integer> {



}
