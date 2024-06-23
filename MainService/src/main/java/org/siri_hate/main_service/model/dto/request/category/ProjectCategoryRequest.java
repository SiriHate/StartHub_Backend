package org.siri_hate.main_service.model.dto.request.category;

import jakarta.validation.constraints.NotBlank;

/**
 * This class represents a request for a ProjectCategory.
 * It includes a field for the name of the category, which is mandatory.
 */
public class ProjectCategoryRequest {

    @NotBlank(message = "Name is required")
    String name;

    /**
     * Default constructor for ProjectCategoryRequest.
     */
    public ProjectCategoryRequest() { }

    /**
     * Constructor for ProjectCategoryRequest with name field.
     *
     * @param name the name of the category
     */
    public ProjectCategoryRequest(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the category.
     *
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name the name of the category
     */
    public void setName(String name) {
        this.name = name;
    }

}