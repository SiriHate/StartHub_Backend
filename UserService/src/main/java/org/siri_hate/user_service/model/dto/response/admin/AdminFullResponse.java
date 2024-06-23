package org.siri_hate.user_service.model.dto.response.admin;

/**
 * DTO for full admin response.
 * This class is used to transfer data related to full admin responses.
 * It contains an ID and a username.
 */
public class AdminFullResponse {

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
     * Initializes a new instance of the AdminFullResponse class.
     */
    public AdminFullResponse() { }

    /**
     * Constructor with parameters.
     * Initializes a new instance of the AdminFullResponse class with an ID and a username.
     *
     * @param id The ID of the admin.
     * @param username The username of the admin.
     */
    public AdminFullResponse(Long id, String username) {
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