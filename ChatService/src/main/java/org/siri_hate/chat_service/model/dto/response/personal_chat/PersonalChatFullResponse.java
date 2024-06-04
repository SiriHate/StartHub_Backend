package org.siri_hate.chat_service.model.dto.response.personal_chat;

import org.siri_hate.chat_service.model.Message;
import org.siri_hate.chat_service.model.User;

import java.util.List;

public class PersonalChatFullResponse {

    private String id;

    private String chatTitle;

    private String chatType;

    private List<User> participants;

    private List<Message> messages;

    public PersonalChatFullResponse() { }

    public PersonalChatFullResponse(
            String id,
            String chatTitle,
            String chatType,
            List<User> participants,
            List<Message> messages
                                   ) {
        this.id = id;
        this.chatTitle = chatTitle;
        this.chatType = chatType;
        this.participants = participants;
        this.messages = messages;
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

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
