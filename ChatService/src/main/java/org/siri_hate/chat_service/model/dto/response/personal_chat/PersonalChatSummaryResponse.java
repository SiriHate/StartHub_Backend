package org.siri_hate.chat_service.model.dto.response.personal_chat;

import java.util.List;

public class PersonalChatSummaryResponse {

    private String id;

    private String chatTitle;

    private String chatType;

    private List<String> participants;

    private String lastMessage;

    public PersonalChatSummaryResponse() { }

    public PersonalChatSummaryResponse(
            String id,
            String chatTitle,
            String chatType,
            List<String> participants,
            String lastMessage
                                      ) {
        this.id = id;
        this.chatTitle = chatTitle;
        this.chatType = chatType;
        this.participants = participants;
        this.lastMessage = lastMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatTitle() {
        return chatTitle;
    }

    public void setChatTitle(String chatTitle) {
        this.chatTitle = chatTitle;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

}
