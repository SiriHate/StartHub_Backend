package org.siri_hate.main_service.model.dto.response.project;

/**
 * This class represents a summary response for a project.
 * It includes the essential details of a project that can be sent as a response.
 */
public class ProjectSummaryResponse {

    private Long id;
    private String projectOwner;
    private String projectName;
    private String projectLogoUrl;
    private String category;
    private Long likes;

    /**
     * Default constructor.
     */
    public ProjectSummaryResponse() { }

    /**
     * Constructor with all fields.
     *
     * @param id             the id of the project
     * @param projectOwner   the owner of the project
     * @param projectName    the name of the project
     * @param projectLogoUrl the logo URL of the project
     * @param category       the category of the project
     * @param likes          the likes of the project
     */
    public ProjectSummaryResponse(
            Long id,
            String projectOwner,
            String projectName,
            String projectLogoUrl,
            String category,
            Long likes
                                 ) {
        this.id = id;
        this.projectOwner = projectOwner;
        this.projectName = projectName;
        this.projectLogoUrl = projectLogoUrl;
        this.category = category;
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
    public String getProjectOwner() {
        return projectOwner;
    }

    /**
     * @param projectOwner the owner to set
     */
    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
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