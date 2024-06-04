package org.siri_hate.chat_service.model.dto.response.group_chat;

import org.siri_hate.chat_service.model.Message;
import org.siri_hate.chat_service.model.User;
import java.util.List;

public class GroupChatFullResponse {

    private String id;

    private String chatTitle;

    private String chatType;

    private List<User> participants;

    private Long participantsNumber;

    private List<Message> messages;

    private String chatOwner;

    public GroupChatFullResponse() { }

    public GroupChatFullResponse(
            String id,
            String chatTitle,
            String chatType,
            List<User> participants,
            Long participantsNumber,
            List<Message> messages,
            String chatOwner
                                ) {
        this.id = id;
        this.chatTitle = chatTitle;
        this.chatType = chatType;
        this.participants = participants;
        this.participantsNumber = participantsNumber;
        this.messages = messages;
        this.chatOwner = chatOwner;
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

    public Long getParticipantsNumber() {
        return participantsNumber;
    }

    public void setParticipantsNumber(Long participantsNumber) {
        this.participantsNumber = participantsNumber;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getChatOwner() {
        return chatOwner;
    }

    public void setChatOwner(String chatOwner) {
        this.chatOwner = chatOwner;
    }

}
