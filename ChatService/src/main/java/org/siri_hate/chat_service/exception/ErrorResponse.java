package org.siri_hate.chat_service.exception;

/**
 * This class represents an error response.
 * It is used to encapsulate an error message in a response when an exception is thrown.
 */
public class ErrorResponse {

    private final String errorMessage;

    /**
     * Constructor for the ErrorResponse class.
     * It initializes the error message.
     *
     * @param errorMessage the error message
     */
    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * This method is used to get the error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

}