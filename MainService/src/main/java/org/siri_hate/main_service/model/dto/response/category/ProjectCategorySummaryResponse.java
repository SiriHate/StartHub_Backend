package org.siri_hate.main_service.model.dto.response.category;

/**
 * This class represents a summary response for a project category.
 * It includes the essential details of a project category that can be sent as a response.
 */
public class ProjectCategorySummaryResponse {

    private Long id;
    private String name;

    /**
     * Default constructor.
     */
    public ProjectCategorySummaryResponse() { }

    /**
     * Constructor with all fields.
     *
     * @param id   the id of the project category
     * @param name the name of the project category
     */
    public ProjectCategorySummaryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id of the project category
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
     * @return the name of the project category
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}