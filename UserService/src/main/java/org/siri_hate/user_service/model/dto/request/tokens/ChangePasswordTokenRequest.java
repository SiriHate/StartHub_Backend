package org.siri_hate.user_service.model.dto.request.tokens;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for change password token request.
 * This class is used to transfer data related to password change requests using a token.
 * It contains a token and a new password, both of which are validated to be not blank.
 */
public class ChangePasswordTokenRequest {

    /**
     * The token used for password change.
     * This is typically a unique identifier used for the password change process.
     * It is validated to be not blank.
     */
    @NotBlank(message = "Token must not be null")
    String token;

    /**
     * The new password of the user.
     * This is typically the new secret string that the user wants to use.
     * It is validated to be not blank and to contain more than 8 characters.
     */
    @NotBlank(message = "New password must not be null")
    @Size(min = 8, message = "New password must contain more than 8 characters")
    String newPassword;

    /**
     * Gets the token used for password change.
     *
     * @return The token used for password change.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token used for password change.
     *
     * @param token The token used for password change.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the new password of the user.
     *
     * @return The new password of the user.
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the new password of the user.
     *
     * @param newPassword The new password of the user.
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}