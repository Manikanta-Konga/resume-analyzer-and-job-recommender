package com.resume.Backend.Model;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobDescription {

    private String title;
    private String description;
    private String skillSet;

}
