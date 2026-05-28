package com.resume.Backend.service.authenticationservice;

import com.resume.Backend.dto.AuthResponseDto;
import com.resume.Backend.dto.LogInReqDto;
import com.resume.Backend.dto.RegisterReqDto;
import com.resume.Backend.dto.RegistrationResDto;
import com.resume.Backend.entity.UserEntity;
import com.resume.Backend.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Constructor Injection
    public AuthService(UserRepo userRepository, JwtService jwtService,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public ResponseEntity<RegistrationResDto> register(RegisterReqDto request) {

        String userName = request.getName();
        String userEmail = request.getEmail();

        if(userRepository.findByEmail(userEmail).isPresent()) {
            throw new RuntimeException("User already existing with this email");
        };

        // Create user object
        UserEntity user = new UserEntity();

        user.setName(userName);
        user.setEmail(userEmail);

        // Encrypt password
        String encryptedPassword =
                passwordEncoder.encode(request.getPassword());

        user.setPassword(encryptedPassword);

        // Save into database
        userRepository.save(user);

        RegistrationResDto registrationResDto = new RegistrationResDto(
                "Registration Successfull",
                HttpStatus.OK.value()
        );

        return new ResponseEntity<>(
                registrationResDto,
                HttpStatus.OK
        );

    }
    public ResponseEntity<AuthResponseDto> login(LogInReqDto request) {

        // Find user
        UserEntity user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Check password
        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid Password");
        }

        // Generate JWT token
        String token =
                jwtService.generateToken(user.getEmail());

        AuthResponseDto authResponseDto = new AuthResponseDto(
                token,
                user.getName()
        );

        // Return token + role
        return new ResponseEntity<>(
                authResponseDto,
                HttpStatus.OK
        );
    }

//    public String login(LogInReqDTO request) {
//
//        // Find user by email
//        UserEntity user = userRepository
//                .findByEmail(request.getEmail())
//                .orElseThrow(() ->
//                        new RuntimeException("User not found"));
//
//        // Compare passwords
//        boolean passwordMatches =
//                passwordEncoder.matches(
//                        request.getPassword(),
//                        user.getPassword()
//                );
//
//        // Check password validity
//        if (!passwordMatches) {
//            throw new RuntimeException("Invalid Password");
//        }
//
//        return "Login Successful";
//    }

}