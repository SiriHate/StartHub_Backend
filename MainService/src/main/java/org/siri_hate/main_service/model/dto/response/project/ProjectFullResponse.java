package org.siri_hate.main_service.model.dto.response.project;

import org.siri_hate.main_service.model.dto.response.project_members.ProjectMembersFullResponse;
import org.siri_hate.main_service.model.dto.response.project_members.ProjectMembersSummaryResponse;
import org.siri_hate.main_service.model.dto.response.user.UserFullResponse;
import org.siri_hate.main_service.model.entity.ProjectMember;

import java.util.Set;

/**
 * This class represents the full response for a project.
 * It includes all the details of a project that can be sent as a response.
 */
public class ProjectFullResponse {

    private Long id;
    private UserFullResponse projectOwner;
    private Set<ProjectMembersSummaryResponse> members;
    private String projectName;
    private String projectDescription;
    private String projectLogoUrl;
    private String category;
    private String stage;
    private Long likes;

    /**
     * Default constructor.
     */
    public ProjectFullResponse() { }

    /**
     * Constructor with all fields.
     *
     * @param id                 the id of the project
     * @param projectOwner       the owner of the project
     * @param members            the members of the project
     * @param projectName        the name of the project
     * @param projectDescription the description of the project
     * @param projectLogoUrl     the logo URL of the project
     * @param category           the category of the project
     * @param stage              the stage of the project
     * @param likes              the likes of the project
     */
    public ProjectFullResponse(
            Long id,
            UserFullResponse projectOwner,
            Set<ProjectMembersSummaryResponse> members,
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

    /**
     * @return the id of the project
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
     * @return the owner of the project
     */
    public UserFullResponse getProjectOwner() {
        return projectOwner;
    }

    /**
     * @param projectOwner the owner to set
     */
    public void setProjectOwner(UserFullResponse projectOwner) {
        this.projectOwner = projectOwner;
    }

    /**
     * @return the members of the project
     */
    public Set<ProjectMembersSummaryResponse> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(Set<ProjectMembersSummaryResponse> members) {
        this.members = members;
    }

    /**
     * @return the name of the project
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the name to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the description of the project
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * @param projectDescription the description to set
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * @return the logo URL of the project
     */
    public String getProjectLogoUrl() {
        return projectLogoUrl;
    }

    /**
     * @param projectLogoUrl the logo URL to set
     */
    public void setProjectLogoUrl(String projectLogoUrl) {
        this.projectLogoUrl = projectLogoUrl;
    }

    /**
     * @return the category of the project
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the stage of the project
     */
    public String getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * @return the likes of the project
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(Long likes) {
        this.likes = likes;
    }

}