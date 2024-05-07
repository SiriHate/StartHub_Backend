package org.siri_hate.user_service.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RecoveryPasswordRequest {

    @NotBlank(message = "Email must not be null")
    String email;

}
