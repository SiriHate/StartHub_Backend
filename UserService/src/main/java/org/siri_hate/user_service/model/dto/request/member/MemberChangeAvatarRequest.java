package org.siri_hate.user_service.model.dto.request.member;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for member avatar change request.
 * This class is used to transfer data related to member avatar change requests.
 * It contains a new avatar URL which is validated to be not blank.
 */
public class MemberChangeAvatarRequest {

    /**
     * The new avatar URL of the member.
     * This is typically the URL of the new avatar image that the member wants to use.
     * It is validated to be not blank.
     */
    @NotBlank(message = "Avatar url must not be null")
    String avatarUrl;

    /**
     * Gets the new avatar URL of the member.
     *
     * @return The new avatar URL of the member.
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Sets the new avatar URL of the member.
     *
     * @param avatarUrl The new avatar URL of the member.
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}