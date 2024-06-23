package org.siri_hate.user_service.exception;

/**
 * Class representing an error response.
 * This class is used to transfer data related to error messages.
 * It contains the error message that will be sent as a response.
 */
public class ErrorResponse {

    /**
     * The error message of the response.
     * This is typically a string that describes the error that occurred.
     */
    private final String errorMessage;

    /**
     * Constructor for the ErrorResponse class.
     *
     * @param errorMessage The error message that will be associated with the response.
     */
    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error message of the response.
     *
     * @return The error message of the response.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

}