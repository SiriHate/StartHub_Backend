package org.siri_hate.user_service.model.dto.response.admin;

/**
 * DTO for AdminSummaryResponse.
 * This class is used to transfer data related to summary admin responses.
 * It contains an ID and a username.
 */
public class AdminSummaryResponse {

    /**
     * The ID of the admin.
     * This is typically a unique identifier for the admin.
     */
    private Long id;

    /**
     * The username of the admin.
     * This is typically a unique identifier for the admin in the system.
     */
    private String username;

    /**
     * Default constructor.
     * Initializes a new instance of the AdminSummaryResponse class.
     */
    public AdminSummaryResponse() { }

    /**
     * Constructor with parameters.
     * Initializes a new instance of the AdminSummaryResponse class with an ID and a username.
     *
     * @param id The ID of the admin.
     * @param username The username of the admin.
     */
    public AdminSummaryResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    /**
     * Gets the ID of the admin.
     *
     * @return The ID of the admin.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the admin.
     *
     * @param id The ID of the admin.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username of the admin.
     *
     * @return The username of the admin.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the admin.
     *
     * @param username The username of the admin.
     */
    public void setUsername(String username) {
        this.username = username;
    }

}