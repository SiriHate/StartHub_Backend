package org.siri_hate.chat_service.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreatePersonalChatRequest {

    @NotBlank(message = "You must specify the interlocutor to create the chat")
    private String recipient;

    public CreatePersonalChatRequest() { }

    public CreatePersonalChatRequest(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

}
