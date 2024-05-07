package org.siri_hate.user_service.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChangePasswordTokenRequest {

    @NotBlank(message = "Token must not be null")
    String token;

    @NotBlank(message = "New password must not be null")
    String newPassword;

}
