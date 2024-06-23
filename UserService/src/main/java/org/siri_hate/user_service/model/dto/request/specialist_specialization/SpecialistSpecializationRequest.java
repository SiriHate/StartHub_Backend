package org.siri_hate.user_service.model.dto.request.specialist_specialization;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for SpecialistSpecializationRequest.
 * This class is used to transfer data related to specialist specialization requests.
 * It contains a name which is validated to be not blank.
 */
public class SpecialistSpecializationRequest {

    /**
     * The name of the specialization.
     * This is typically the name of the specialist's area of expertise.
     * It is validated to be not blank.
     */
    @NotBlank(message = "Specialization name is required")
    private String name;

    /**
     * Default constructor.
     * Initializes a new instance of the SpecialistSpecializationRequest class.
     */
    public SpecialistSpecializationRequest() { }

    /**
     * Constructor with parameters.
     * Initializes a new instance of the SpecialistSpecializationRequest class with a name.
     *
     * @param name The name of the specialization.
     */
    public SpecialistSpecializationRequest(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the specialization.
     *
     * @return The name of the specialization.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the specialization.
     *
     * @param name The name of the specialization.
     */
    public void setName(String name) {
        this.name = name;
    }

}