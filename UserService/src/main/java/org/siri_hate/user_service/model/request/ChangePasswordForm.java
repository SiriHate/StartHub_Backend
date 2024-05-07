package org.siri_hate.user_service.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordForm {

    @NotBlank
    String currentPassword;

    @NotBlank
    @Size(min = 8, message = "Password must contain more than 8 characters")
    String newPassword;

}
