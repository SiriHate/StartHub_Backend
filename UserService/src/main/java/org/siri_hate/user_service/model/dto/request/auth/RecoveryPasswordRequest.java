package org.siri_hate.user_service.model.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for recovery password request.
 * This class is used to transfer data related to password recovery requests.
 * It contains an email which is validated to be not blank.
 */
public class RecoveryPasswordRequest {

    /**
     * The email of the user.
     * This is typically the email address of the user who wants to recover their password.
     * It is validated to be not blank.
     */
    @NotBlank(message = "Email must not be null")
    private String email;

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

}