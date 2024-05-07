package org.siri_hate.main_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_name")
    @NotBlank(message = "Project name should not be null")
    String projectName;

    @Lob
    @Column(name = "project_picture")
    byte[] projectPicture;

    @Column(name = "project_owner_username")
    @NotBlank(message = "Project owner username should not be null")
    String projectOwnerUsername;

    @Column(name = "project_description")
    @NotBlank(message = "Project description should not be null")
    String projectDescription;

    @Column(name = "project_likes")
    Long projectLikes;
    
}
