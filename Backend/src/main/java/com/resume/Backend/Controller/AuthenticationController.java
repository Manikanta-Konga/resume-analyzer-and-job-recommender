package com.resume.Backend.controller;


import com.resume.Backend.dto.AuthResponse;
import com.resume.Backend.dto.LogInReqDTO;
import com.resume.Backend.dto.RegisterRequest;
import com.resume.Backend.service.authenticationservice.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthService authService;

    // Constructor Injection
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        authService.register(request);

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LogInReqDTO request) {

        return authService.login(request);
    }


}
