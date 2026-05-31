package com.resume.Backend.service.authenticationservice;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "mySuperSecretKeyForJwtAuthenticationProject123456";

    // Generate Token
    public String generateToken(String email) {

        return Jwts.builder()

                .setSubject(email)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )

                .signWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes()
                        ),
                        SignatureAlgorithm.HS256
                )


                .compact();
    }

    // Extract email from token
    public String extractEmail(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();


//                Jwts.parser()
//
//                .setSigningKey(SECRET_KEY)
//
//                .parseClaimsJws(token)
//
//                .getBody();

        return claims.getSubject();
    }

    // Validate token
    public boolean validateToken(String token,
                                 String email) {

        String extractedEmail =
                extractEmail(token);

        return extractedEmail.equals(email);
    }
}

