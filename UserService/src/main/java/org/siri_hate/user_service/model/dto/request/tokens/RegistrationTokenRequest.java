package org.siri_hate.user_service.model.dto.request.tokens;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for registration token request.
 * This class is used to transfer data related to registration requests using a token.
 * It contains a token which is validated to be not blank.
 */
public class RegistrationTokenRequest {

    /**
     * The token used for registration.
     * This is typically a unique identifier used for the registration process.
     * It is validated to be not blank.
     */
    @NotBlank(message = "Token must not be null")
    private String token;

    /**
     * Gets the token used for registration.
     *
     * @return The token used for registration.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token used for registration.
     *
     * @param token The token used for registration.
     */
    public void setToken(String token) {
        this.token = token;
    }

}