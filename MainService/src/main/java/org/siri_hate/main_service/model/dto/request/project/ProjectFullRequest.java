package org.siri_hate.main_service.model.dto.request.project;

import jakarta.validation.constraints.NotBlank;
import org.siri_hate.main_service.model.entity.ProjectMember;

import java.util.HashSet;
import java.util.Set;

public class ProjectFullRequest {
    
    private Set<ProjectMember> members = new HashSet<>();

    @NotBlank
    private String projectName;

    @NotBlank
    private String projectDescription;

    private String projectLogoUrl;

    @NotBlank
    private String category;

    @NotBlank
    private String stage;

    public ProjectFullRequest() {}

    public ProjectFullRequest(
            Set<ProjectMember> members,
            String projectName,
            String projectDescription,
            String projectLogoUrl,
            String category,
            String stage
                             ) {
        this.members = members;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectLogoUrl = projectLogoUrl;
        this.category = category;
        this.stage = stage;
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

}
