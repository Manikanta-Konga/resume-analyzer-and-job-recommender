package com.resume.Backend.controller;

//import com.resume.Backend.dto.JobRequestDTO;
//import com.resume.Backend.storingindb.JobStoringService;
import com.resume.Backend.dto.responseDto.ResumeAnalysisDto;
import com.resume.Backend.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final ResumeService service;

    public UserController(ResumeService service) {
        this.service = service;
    }

    @PostMapping("/uploadResume")
    public ResponseEntity<ResumeAnalysisDto> uploadResume(@RequestParam("File") MultipartFile resFile) throws IOException {
        return ResponseEntity.status(200).body(service.uploadResume(resFile));
    }

}
