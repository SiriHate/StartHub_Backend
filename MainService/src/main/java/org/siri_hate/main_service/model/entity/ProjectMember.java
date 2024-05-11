package org.siri_hate.main_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project_membership")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotBlank(message = "Username should not be blank")
    private String username;

    @Column(name = "role")
    @NotBlank(message = "Role should not be blank")
    private String role;

    @ManyToMany(mappedBy = "members")
    @JsonIgnore
    private Set<Project> projects = new HashSet<>();;

}
