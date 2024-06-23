package org.siri_hate.chat_service.model.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * This class represents a request to create a new personal chat.
 * It contains the recipient's username.
 */
public class CreatePersonalChatRequest {

    /**
     * The username of the recipient.
     * This field cannot be blank.
     */
    @NotBlank(message = "You must specify the interlocutor to create the chat")
    private String recipient;

    /**
     * Default constructor for the CreatePersonalChatRequest class.
     */
    public CreatePersonalChatRequest() { }

    /**
     * Constructor for the CreatePersonalChatRequest class.
     * It initializes the recipient's username.
     *
     * @param recipient the username of the recipient
     */
    public CreatePersonalChatRequest(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Getter for the recipient's username.
     *
     * @return the username of the recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Setter for the recipient's username.
     *
     * @param recipient the new username of the recipient
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

}