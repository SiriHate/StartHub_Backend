package org.siri_hate.main_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_owner")
    private String projectOwner;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "project_members", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<ProjectMember> members = new HashSet<>();

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "project_logo_url")
    private String projectLogoUrl;

    @Column(name = "category")
    private String category;

    @Column(name = "stage")
    private String stage;

    @Column(name = "likes")
    private Long likes;

    public Project() {}

    public Project(
            Long id,
            String projectOwner,
            Set<ProjectMember> members,
            String projectName,
            String projectDescription,
            String projectLogoUrl,
            String category,
            String stage,
            Long likes
                  ) {
        this.id = id;
        this.projectOwner = projectOwner;
        this.members = members;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectLogoUrl = projectLogoUrl;
        this.category = category;
        this.stage = stage;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public Set<ProjectMember> getMembers() {
        return members;
    }

    public void setMembers(Set<ProjectMember> members) {
        this.members = members;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectLogoUrl() {
        return projectLogoUrl;
    }

    public void setProjectLogoUrl(String projectLogoUrl) {
        this.projectLogoUrl = projectLogoUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(projectOwner, project.projectOwner) && Objects.equals(
                members,
                project.members
                                                                                                                     ) && Objects.equals(
                projectName,
                project.projectName
                                                                                                                                        ) && Objects.equals(
                projectDescription,
                project.projectDescription
                                                                                                                                                           ) && Objects.equals(
                projectLogoUrl,
                project.projectLogoUrl
                                                                                                                                                                              ) && Objects.equals(
                category,
                project.category
                                                                                                                                                                                                 ) && Objects.equals(
                stage,
                project.stage
                                                                                                                                                                                                                    ) && Objects.equals(
                likes,
                project.likes
                                                                                                                                                                                                                                       );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                projectOwner,
                members,
                projectName,
                projectDescription,
                projectLogoUrl,
                category,
                stage,
                likes
                           );
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectOwner='" + projectOwner + '\'' +
                ", members=" + members +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectLogoUrl='" + projectLogoUrl + '\'' +
                ", category='" + category + '\'' +
                ", stage='" + stage + '\'' +
                ", likes=" + likes +
                '}';
    }

}
