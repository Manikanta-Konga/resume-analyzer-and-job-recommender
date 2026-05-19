package com.resume.Backend.controller;

//import com.resume.Backend.dto.JobRequestDTO;
import com.resume.Backend.dto.JobRecommendationDto;
import com.resume.Backend.dto.ResponseDTO;
import com.resume.Backend.dto.ResumeDataDTO;
//import com.resume.Backend.storingindb.JobStoringService;
import com.resume.Backend.service.ResumeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final ResumeService service;

    public UserController(ResumeService service) {
        this.service = service;
    }

    @PostMapping("/uploadResume")
    public ResponseEntity<List<JobRecommendationDto>> uploadResume(@RequestParam("File") MultipartFile resFile) throws IOException {
        return ResponseEntity.status(200).body(service.uploadResume(resFile));
    }


}
