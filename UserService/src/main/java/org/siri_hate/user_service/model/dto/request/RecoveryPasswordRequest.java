package org.siri_hate.user_service.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RecoveryPasswordRequest {

    @NotBlank(message = "Email must not be null")
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
