package com.resume.Backend.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersModel {

    @Id
    private int userId;

    private String name;
    private String email;
    private String password;
}
