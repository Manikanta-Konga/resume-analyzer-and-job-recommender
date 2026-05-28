package com.resume.Backend.dto;

public class AuthResponseDto {

    private String token;

    private String userName;

    public AuthResponseDto(String token,
                           String userName) {

        this.token = token;
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public String getUserName() {
        return userName;
    }
}
