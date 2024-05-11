package org.siri_hate.main_service.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_owner")
    private String projectOwner;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "project_members", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<ProjectMember> members = new HashSet<>();

    @Column(name = "project_name")
    @NotBlank(message = "Project name should not be null")
    private String projectName;

    @Column(name = "project_description")
    @NotBlank(message = "Project description should not be null")
    private String projectDescription;

    @Column(name = "project_logo_url")
    private String projectLogoUrl;

    @Column(name = "category")
    private String category;

    @Column(name = "stage")
    private String stage;

    @Column(name = "likes")
    private Long likes = 0L;

}
