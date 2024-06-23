package org.siri_hate.user_service.model.dto.request.admin;

/**
 * DTO for full admin request.
 * This class is used to transfer data related to admin requests.
 * It contains the username and password of the admin.
 */
public class AdminFullRequest {

    /**
     * The username of the admin.
     * This is typically a unique identifier for the admin in the system.
     */
    private String username;

    /**
     * The password of the admin.
     * This is typically a secret string that the admin uses to authenticate themselves.
     */
    private String password;

    /**
     * Default constructor for the AdminFullRequest class.
     */
    public AdminFullRequest() { }

    /**
     * Constructor for the AdminFullRequest class.
     *
     * @param username The username of the admin.
     * @param password The password of the admin.
     */
    public AdminFullRequest(String username, String password) {
        this.username = username;
        this.password = password;
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

    /**
     * Gets the password of the admin.
     *
     * @return The password of the admin.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the admin.
     *
     * @param password The password of the admin.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}