package org.siri_hate.user_service.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrationTokenRequest {

    @NotBlank(message = "Token must not be null")
    private String token;

}
