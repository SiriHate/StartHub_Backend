package org.siri_hate.user_service.model.forms;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {

    @NotBlank
    String login;

    @NotBlank
    String password;

}
