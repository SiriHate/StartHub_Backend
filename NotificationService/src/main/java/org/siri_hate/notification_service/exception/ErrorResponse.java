package org.siri_hate.notification_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents the structure of an error response.
 * It is used to send a standardized error message to the client when an exception occurs.
 * It uses Lombok annotations for automatic generation of getters, setters, and constructors.
 */
@AllArgsConstructor
@Data
public class ErrorResponse {

    /**
     * The error message that will be sent to the client.
     */
    private final String error;

}