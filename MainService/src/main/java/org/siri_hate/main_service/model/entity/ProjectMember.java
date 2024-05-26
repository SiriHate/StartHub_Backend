package org.siri_hate.main_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "project_membership")
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
    private Set<Project> projects = new HashSet<>();;

    public ProjectMember() {}

    public ProjectMember(Long id, String username, String role, Set<Project> projects) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMember that = (ProjectMember) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(role, that.role) && Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, role, projects);
    }

    @Override
    public String toString() {
        return "ProjectMember{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", projects=" + projects +
                '}';
    }

}
