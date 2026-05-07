package com.resume.Backend.controller;

import com.resume.Backend.DTO.JobRequestDTO;
import com.resume.Backend.DTO.ResponseDTO;
import com.resume.Backend.service.storingInDB.JobStoringService;
import com.resume.Backend.service.resume_main.ResumeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ResumeController {

    private final ResumeService service;
    private final JobStoringService jobService;

    ResumeController(ResumeService service, JobStoringService jobService) {
        this.service = service;
        this.jobService = jobService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<ResponseDTO>> uploadResume(@RequestParam("File") MultipartFile resFile) throws IOException {
        return ResponseEntity.status(200).body(service.uploadResume(resFile));
    }

    @PostMapping("/jobs")
    public ResponseEntity<JobRequestDTO> uploadJobs(@Valid @RequestBody JobRequestDTO jobRequest) {
        JobRequestDTO savedJob = jobService.saveJobInDB(jobRequest);
        return new ResponseEntity<>(savedJob, HttpStatus.ACCEPTED);
    }

}
