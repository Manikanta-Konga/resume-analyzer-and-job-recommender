package com.resume.Backend.Repository;


import com.resume.Backend.Model.ResumeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepo extends JpaRepository<ResumeModel, Long> {

}
