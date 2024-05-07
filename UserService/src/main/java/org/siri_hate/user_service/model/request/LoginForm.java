package org.siri_hate.user_service.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {

    @NotBlank(message = "Username must not be null")
    String username;

    @NotBlank(message = "Password must not be null")
    String password;

}
