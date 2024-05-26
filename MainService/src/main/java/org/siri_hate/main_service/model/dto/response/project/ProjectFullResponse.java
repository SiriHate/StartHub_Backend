package org.siri_hate.main_service.model.dto.response.project;

import org.siri_hate.main_service.model.entity.ProjectMember;

import java.util.Set;

public class ProjectFullResponse {

    private Long id;

    private String projectOwner;

    private Set<ProjectMember> members;

    private String projectName;

    private String projectDescription;

    private String projectLogoUrl;

    private String category;

    private String stage;

    private Long likes;

    public ProjectFullResponse() {}

    public ProjectFullResponse(
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

}
