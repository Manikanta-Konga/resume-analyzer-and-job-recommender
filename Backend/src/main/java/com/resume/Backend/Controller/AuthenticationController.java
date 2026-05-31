package com.resume.Backend.controller;


import com.resume.Backend.dto.responseDto.AuthResponseDto;
import com.resume.Backend.dto.requestDto.LogInReqDto;
import com.resume.Backend.dto.requestDto.RegisterReqDto;
import com.resume.Backend.dto.responseDto.RegistrationResDto;
import com.resume.Backend.service.authenticationservice.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RegistrationResDto> register(
            @Valid @RequestBody RegisterReqDto request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @Valid @RequestBody LogInReqDto request) {

        return authService.login(request);
    }


}
