package org.siri_hate.main_service.exception;

/**
 * This class represents a standard error response for the application.
 * It is used in the global exception handler to return a consistent error message format.
 */
public class ErrorResponse {

    private final String errorMessage;

    /**
     * Constructor for the ErrorResponse class.
     * It initializes the error message.
     *
     * @param errorMessage the message describing the error
     */
    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * This method retrieves the error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

}