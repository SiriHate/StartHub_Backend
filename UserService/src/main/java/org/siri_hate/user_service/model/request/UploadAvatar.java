package org.siri_hate.user_service.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UploadAvatar {

    @NotNull(message = "Avatar must not be null")
    Byte[] avatar;

}
