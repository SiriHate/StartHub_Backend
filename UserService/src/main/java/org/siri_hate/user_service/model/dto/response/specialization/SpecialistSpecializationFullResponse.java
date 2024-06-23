package org.siri_hate.user_service.model.dto.response.specialization;

/**
 * DTO for full specialist specialization response.
 * This class is used to transfer data related to full specialist specialization responses.
 * It contains an ID and a name.
 */
public class SpecialistSpecializationFullResponse {

    /**
     * The ID of the specialist specialization.
     * This is typically a unique identifier for the specialization.
     */
    private Long id;

    /**
     * The name of the specialist specialization.
     * This is typically a descriptive name for the specialization.
     */
    private String name;

    /**
     * Default constructor.
     * Initializes a new instance of the SpecialistSpecializationFullResponse class.
     */
    public SpecialistSpecializationFullResponse() { }

    /**
     * Constructor with parameters.
     * Initializes a new instance of the SpecialistSpecializationFullResponse class with an ID and a name.
     *
     * @param id The ID of the specialist specialization.
     * @param name The name of the specialist specialization.
     */
    public SpecialistSpecializationFullResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the ID of the specialist specialization.
     *
     * @return The ID of the specialist specialization.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the specialist specialization.
     *
     * @param id The ID of the specialist specialization.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the specialist specialization.
     *
     * @return The name of the specialist specialization.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the specialist specialization.
     *
     * @param name The name of the specialist specialization.
     */
    public void setName(String name) {
        this.name = name;
    }

}