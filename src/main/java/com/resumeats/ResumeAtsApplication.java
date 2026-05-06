package com.resumeats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResumeAtsApplication {
    public static void main(String[] args) {
        // This forces Spring Boot to use 8081, no matter what!
        System.setProperty("server.port", "8081");
        SpringApplication.run(ResumeAtsApplication.class, args);
    }
}