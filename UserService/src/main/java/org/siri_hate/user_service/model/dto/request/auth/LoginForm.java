package org.siri_hate.user_service.model.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for LoginForm.
 * This class is used to transfer data related to login requests.
 * It contains a username and a password, both of which are validated to be not blank.
 */
public class LoginForm {

    /**
     * The username of the user.
     * This is typically a unique identifier for the user in the system.
     * It is validated to be not blank.
     */
    @NotBlank(message = "Username must not be null")
    private String username;

    /**
     * The password of the user.
     * This is typically a secret string that the user uses to authenticate themselves.
     * It is validated to be not blank.
     */
    @NotBlank(message = "Password must not be null")
    private String password;

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}