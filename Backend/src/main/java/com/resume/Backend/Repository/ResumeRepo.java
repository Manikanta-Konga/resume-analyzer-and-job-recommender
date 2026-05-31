package com.resume.Backend.repository;


import com.resume.Backend.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepo extends JpaRepository<ResumeEntity, Integer> {

}
