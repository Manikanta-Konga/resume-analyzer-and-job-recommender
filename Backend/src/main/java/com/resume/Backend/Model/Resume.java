package com.resume.Backend.Model;


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
public class Resume {

    @Id
    private Long id;

    private String fileType;
    private String content;
    private LocalDate uploadedAt;
    private String userName;


}
