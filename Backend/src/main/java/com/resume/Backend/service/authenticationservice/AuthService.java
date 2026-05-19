package com.resume.Backend.service.authenticationservice;

import com.resume.Backend.dto.AuthResponse;
import com.resume.Backend.dto.LogInReqDTO;
import com.resume.Backend.dto.RegisterRequest;
import com.resume.Backend.entity.Role;
import com.resume.Backend.entity.UserEntity;
import com.resume.Backend.repository.UserRepo;
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

    public void register(RegisterRequest request) {

        // Create user object
        UserEntity user = new UserEntity();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Encrypt password
        String encryptedPassword =
                passwordEncoder.encode(request.getPassword());

        user.setPassword(encryptedPassword);

        // Assign USER role
//        user.setRole(Role.USER);

        // Save into database
        userRepository.save(user);
    }
    public AuthResponse login(LogInReqDTO request) {

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

        // Return token + role
        return new AuthResponse(
                token,
                user.getName()
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