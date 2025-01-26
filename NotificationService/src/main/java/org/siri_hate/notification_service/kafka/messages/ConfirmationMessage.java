package org.siri_hate.notification_service.kafka.messages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.siri_hate.notification_service.model.enums.ConfirmationMessageType;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfirmationMessage {


    @NotNull(message = "Message type should not be null")
    private ConfirmationMessageType messageType;


    @NotBlank(message = "User full name should not be null")
    private String userFullName;


    @NotBlank(message = "Email should not be null")
    private String userEmail;


    @NotBlank(message = "Confirmation token should not be null")
    private String userConfirmationToken;

}