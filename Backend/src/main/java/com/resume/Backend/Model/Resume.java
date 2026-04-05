package com.resume.Backend.Model;


import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Resume {

    private String fileType;
    private String content;
    private LocalDate uploadedAt;
    private String userName;


}
