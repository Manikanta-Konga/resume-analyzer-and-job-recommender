package com.resume.Backend.Service;

import com.resume.Backend.Repository.ResumeRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeService {

    private final ResumeRepo repo;

    ResumeService(ResumeRepo repo) {
        this.repo = repo;
    }

    public String uploadResume(MultipartFile resFile) {
        String fileName = resFile.getOriginalFilename();
        long size = resFile.getSize();
        double fileSize = (double) size / (1024 * 1024);
        if(fileSize <= 5) {
            throw new RuntimeException("File size too large");
        }
//        assert fileName != null : ;
        if((fileName.endsWith(".pdf") || fileName.endsWith(".docx"))) {
            if(resFile.isEmpty()) {
                throw new RuntimeException("File is Empty, again upload resume");
            }
        } else {
            throw new RuntimeException("File type is not supported");
        }

        return "Resume File is Uploaded Successfully";
    }



}
