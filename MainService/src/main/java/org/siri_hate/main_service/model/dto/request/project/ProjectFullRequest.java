package org.siri_hate.main_service.model.dto.request.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.siri_hate.main_service.model.dto.request.project_members.ProjectMemberRequest;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a full request for a Project.
 * It includes fields for the project members, project name, project description, project logo URL, category ID, and stage of the project.
 * The project name, project description, and stage fields are mandatory, and the category ID must be a positive number.
 */
public class ProjectFullRequest {

    /**
     * A set of project members.
     */
    private Set<ProjectMemberRequest> members = new HashSet<>();

    @NotBlank(message = "Project name should not be blank")
    private String projectName;

    @NotBlank(message = "Project description should not be blank")
    private String projectDescription;

    private String projectLogoUrl;

    @Positive(message = "Category id should be positive")
    private Long categoryId;

    @NotBlank(message = "Stage should not be blank")
    private String stage;

    /**
     * Default constructor for ProjectFullRequest.
     */
    public ProjectFullRequest() { }

    /**
     * Constructor for ProjectFullRequest with all fields.
     *
     * @param members            the project members
     * @param projectName        the name of the project
     * @param projectDescription the description of the project
     * @param projectLogoUrl     the logo URL of the project
     * @param categoryId         the category ID of the project
     * @param stage              the stage of the project
     */
    public ProjectFullRequest(
            Set<ProjectMemberRequest> members,
            String projectName,
            String projectDescription,
            String projectLogoUrl,
            Long categoryId,
            String stage
                             ) {
        this.members = members;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectLogoUrl = projectLogoUrl;
        this.categoryId = categoryId;
        this.stage = stage;
    }

    /**
     * Returns the project members.
     *
     * @return the project members
     */
    public Set<ProjectMemberRequest> getMembers() {
        return members;
    }

    /**
     * Sets the project members.
     *
     * @param members the project members
     */
    public void setMembers(Set<ProjectMemberRequest> members) {
        this.members = members;
    }

    /**
     * Returns the name of the project.
     *
     * @return the name of the project
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the name of the project.
     *
     * @param projectName the name of the project
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Returns the description of the project.
     *
     * @return the description of the project
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * Sets the description of the project.
     *
     * @param projectDescription the description of the project
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * Returns the logo URL of the project.
     *
     * @return the logo URL of the project
     */
    public String getProjectLogoUrl() {
        return projectLogoUrl;
    }

    /**
     * Sets the logo URL of the project.
     *
     * @param projectLogoUrl the logo URL of the project
     */
    public void setProjectLogoUrl(String projectLogoUrl) {
        this.projectLogoUrl = projectLogoUrl;
    }

    /**
     * Returns the category ID of the project.
     *
     * @return the category ID of the project
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID of the project.
     *
     * @param categoryId the category ID of the project
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Returns the stage of the project.
     *
     * @return the stage of the project
     */
    public String getStage() {
        return stage;
    }

    /**
     * Sets the stage of the project.
     *
     * @param stage the stage of the project
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

}