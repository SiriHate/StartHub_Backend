package org.siri_hate.notification_service.kafka.messages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.siri_hate.notification_service.model.enums.ConfirmationMessageType;

/**
 * This class represents a confirmation message that is used in the Kafka messaging system.
 * It uses Lombok annotations for automatic generation of getters, setters, and constructors.
 * It uses Jakarta validation constraints to ensure that the fields are not null or blank.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfirmationMessage {

    /**
     * The type of the confirmation message.
     * It should not be null.
     */
    @NotNull(message = "Message type should not be null")
    private ConfirmationMessageType messageType;

    /**
     * The full name of the user.
     * It should not be null or blank.
     */
    @NotBlank(message = "User full name should not be null")
    private String userFullName;

    /**
     * The email of the user.
     * It should not be null or blank.
     */
    @NotBlank(message = "Email should not be null")
    private String userEmail;

    /**
     * The confirmation token of the user.
     * It should not be null or blank.
     */
    @NotBlank(message = "Confirmation token should not be null")
    private String userConfirmationToken;

}