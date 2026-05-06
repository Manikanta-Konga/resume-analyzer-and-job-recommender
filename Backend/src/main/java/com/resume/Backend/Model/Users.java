package com.resume.Backend.Model;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {


    private int userId;

    private String name;
    private String email;
    private String password;
}
