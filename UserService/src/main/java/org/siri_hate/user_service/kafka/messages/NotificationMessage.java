package org.siri_hate.user_service.kafka.messages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.siri_hate.user_service.model.enums.NotificationMessageType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationMessage {

    @NotNull(message = "Message type should not be null")
    private NotificationMessageType messageType;

    @NotBlank(message = "User full name should not be null")
    private String userFullName;

    @NotBlank(message = "User email should not be null")
    private String userEmail;

}
