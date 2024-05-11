package org.siri_hate.user_service.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AvatarRequest {

    @NotBlank(message = "Avatar url must not be null")
    String avatarUrl;

}
