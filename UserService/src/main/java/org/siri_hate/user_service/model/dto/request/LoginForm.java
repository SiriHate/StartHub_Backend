package org.siri_hate.user_service.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LoginForm {

    @NotBlank(message = "Username must not be null")
    String username;

    @NotBlank(message = "Password must not be null")
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
