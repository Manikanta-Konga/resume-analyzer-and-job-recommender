package com.resume.Backend.Controller;

import com.resume.Backend.Service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ResumeController {

    private final ResumeService service;

    ResumeController(ResumeService service) {
        this.service = service;
    }

    @GetMapping("/Get")
    public ResponseEntity<String> testApplication(){
        return new ResponseEntity<>("Application is Running", HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("File") MultipartFile resFile) {
        return ResponseEntity.status(200).body(service.uploadResume(resFile));
    }


}
