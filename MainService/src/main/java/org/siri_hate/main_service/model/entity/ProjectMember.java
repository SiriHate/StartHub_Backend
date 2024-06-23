package org.siri_hate.main_service.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the ProjectMember entity.
 * It includes the id, user, role, and projects of the project member.
 * It is mapped to the "project_members" table in the database.
 */
@Entity
@Table(name = "project_members")
public class ProjectMember {

    /**
     * The id of the project member.
     * It is the primary key in the "project_members" table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who is a member of the project.
     * It is a foreign key referencing the "users" table.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The role of the user in the project.
     */
    @Column(name = "role")
    @NotBlank(message = "Role should not be blank")
    private String role;

    /**
     * The projects that the user is a member of.
     * It is a many-to-many relationship with the "projects" table.
     */
    @ManyToMany(mappedBy = "members")
    @JsonIgnoreProperties("members")
    private Set<Project> projects = new HashSet<>();

    /**
     * Default constructor.
     */
    public ProjectMember() { }

    /**
     * Constructor with user and role fields.
     *
     * @param user the user who is a member of the project
     * @param role the role of the user in the project
     */
    public ProjectMember(User user, String role) {
        this.user = user;
        this.role = role;
    }

    /**
     * @return the id of the project member
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the user who is a member of the project
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the role of the user in the project
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the projects that the user is a member of
     */
    public Set<Project> getProjects() {
        return projects;
    }

    /**
     * @param projects the projects to set
     */
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

}