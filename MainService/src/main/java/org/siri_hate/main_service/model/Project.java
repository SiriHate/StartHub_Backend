package org.siri_hate.main_service.model;

import jakarta.persistence.*;
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
    String projectName;

    @Column(name = "project_picture")
    Byte projectPicture;

    @Column(name = "project_owner_id")
    Long projectOwnerId;

    Set<Long> projectTeam;

    @Column(name = "project_description")
    String projectDescription;

    @Column(name = "project_likes")
    Long projectLikes;
    
}
