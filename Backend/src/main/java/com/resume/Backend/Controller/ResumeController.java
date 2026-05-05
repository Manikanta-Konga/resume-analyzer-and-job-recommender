package com.resume.Backend.Controller;

import com.resume.Backend.Service.resume.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public ResponseEntity<String> uploadResume(@RequestParam("File") MultipartFile resFile) throws IOException {
        return ResponseEntity.status(200).body(service.uploadResume(resFile));
    }


}
