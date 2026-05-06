package com.resume.Backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeModel {

    @Id
    private Long resumeId;

    private String fileType;
    private String content;
    private LocalDate uploadedAt;
    private String userName;


}
