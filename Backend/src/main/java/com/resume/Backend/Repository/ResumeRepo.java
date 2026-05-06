package com.resume.Backend.repository;


import com.resume.Backend.model.ResumeEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepo extends JpaRepository<ResumeEntityModel, Integer> {

}
