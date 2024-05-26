package org.siri_hate.chat_service.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreatePersonalChatRequest {

    @NotBlank
    private String sender;

    @NotBlank
    private String recipient;

    public CreatePersonalChatRequest() {}

    public CreatePersonalChatRequest(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

}
