package org.siri_hate.main_service.model.dto.request.category;

import jakarta.validation.constraints.NotBlank;

/**
 * This class represents a request for a NewsCategory.
 * It includes a field for the name of the category, which is mandatory.
 */
public class NewsCategoryRequest {

    @NotBlank(message = "Name is required")
    String name;

    /**
     * Default constructor for NewsCategoryRequest.
     */
    public NewsCategoryRequest() { }

    /**
     * Constructor for NewsCategoryRequest with name field.
     *
     * @param name the name of the category
     */
    public NewsCategoryRequest(String name) {
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