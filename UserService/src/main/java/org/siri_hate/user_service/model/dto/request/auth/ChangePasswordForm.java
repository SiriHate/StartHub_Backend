package org.siri_hate.user_service.model.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for ChangePasswordForm.
 * This class is used to transfer data related to password change requests.
 * It contains the current password and a new password, both of which are validated to be not blank.
 * The new password is also validated to contain more than 8 characters.
 */
public class ChangePasswordForm {

    /**
     * The current password of the user.
     * This is typically the current secret string that the user uses to authenticate themselves.
     * It is validated to be not blank.
     */
    @NotBlank(message = "Current password must not be null")
    private String currentPassword;

    /**
     * The new password of the user.
     * This is typically the new secret string that the user wants to use.
     * It is validated to be not blank and to contain more than 8 characters.
     */
    @NotBlank
    @Size(min = 8, message = "New password must contain more than 8 characters")
    private String newPassword;

    /**
     * Gets the current password of the user.
     *
     * @return The current password of the user.
     */
    public String getCurrentPassword() {
        return currentPassword;
    }

    /**
     * Sets the current password of the user.
     *
     * @param currentPassword The current password of the user.
     */
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
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