package org.siri_hate.user_service.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ChangePasswordTokenRequest {

    @NotBlank(message = "Token must not be null")
    String token;

    @NotBlank(message = "New password must not be null")
    String newPassword;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
