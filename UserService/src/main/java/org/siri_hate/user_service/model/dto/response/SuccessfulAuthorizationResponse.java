package org.siri_hate.user_service.model.dto.response;

/**
 * DTO for successful authorization response.
 * This class is used to transfer data related to successful authorization responses.
 * It contains a message and a token.
 */
public class SuccessfulAuthorizationResponse {

    /**
     * The message of the successful authorization response.
     * This is typically a success message.
     */
    private String message;

    /**
     * The token of the successful authorization response.
     * This is the authorization token generated upon successful authorization.
     */
    private String token;

    /**
     * Default constructor.
     * Initializes a new instance of the SuccessfulAuthorizationResponse class.
     */
    public SuccessfulAuthorizationResponse() { }

    /**
     * Constructor with parameters.
     * Initializes a new instance of the SuccessfulAuthorizationResponse class with a token.
     *
     * @param token The token of the successful authorization response.
     */
    public SuccessfulAuthorizationResponse(String token) {
        this.token = token;
    }

    /**
     * Gets the message of the successful authorization response.
     *
     * @return The message of the successful authorization response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the successful authorization response.
     *
     * @param message The message of the successful authorization response.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the token of the successful authorization response.
     *
     * @return The token of the successful authorization response.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token of the successful authorization response.
     *
     * @param token The token of the successful authorization response.
     */
    public void setToken(String token) {
        this.token = token;
    }

}