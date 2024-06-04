package org.siri_hate.chat_service.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public class CreateGroupChatRequest {

    @NotBlank
    String chatTitle;

    @Size(min = 1, max = 100, message = "Group chat can contain from 1 to 100 users!")
    private List<String> participants;

    public CreateGroupChatRequest() { }

    public CreateGroupChatRequest(String chatTitle, List<String> participants) {
        this.chatTitle = chatTitle;
        this.participants = participants;
    }

    public String getChatTitle() {
        return chatTitle;
    }

    public void setChatTitle(String chatTitle) {
        this.chatTitle = chatTitle;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

}
