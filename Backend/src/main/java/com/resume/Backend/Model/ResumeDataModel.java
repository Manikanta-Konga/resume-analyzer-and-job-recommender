package com.resume.Backend.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ResumeDataModel {

    @Id
    private int resumeId;

    private String userName;
    private String email;
    private String phoneNo;
    private String skills;


}
