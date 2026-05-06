package com.resume.Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobDescriptionModel {

    @Id
    private Long id;

    private String title;
    private String description;
    private String skillSet;

}
