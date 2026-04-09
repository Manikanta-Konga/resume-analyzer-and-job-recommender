package com.resume.Backend.Controller;

import com.resume.Backend.Service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ResumeController {

    private final ResumeService service;

    ResumeController(ResumeService service) {
        this.service = service;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> upoloadResume(@RequestBody MultipartFile resFile) {
        return ResponseEntity.status(200).body(service.uploadResume(resFile));
    }


}
