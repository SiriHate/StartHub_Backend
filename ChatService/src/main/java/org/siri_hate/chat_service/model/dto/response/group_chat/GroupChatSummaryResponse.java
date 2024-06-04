package org.siri_hate.chat_service.model.dto.response.group_chat;

import org.siri_hate.chat_service.model.Message;
import java.util.List;

public class GroupChatSummaryResponse {

    private String id;

    private String chatTitle;

    private String chatType;

    private List<String> participants;

    private Long participantsNumber;

    private List<String> messages;

    private String chatOwner;

    private Message lastMessage;

    public GroupChatSummaryResponse() { }

    public GroupChatSummaryResponse(
            String id,
            String chatTitle,
            String chatType,
            List<String> participants,
            Long participantsNumber,
            List<String> messages,
            String chatOwner,
            Message lastMessage
                                   ) {
        this.id = id;
        this.chatTitle = chatTitle;
        this.chatType = chatType;
        this.participants = participants;
        this.participantsNumber = participantsNumber;
        this.messages = messages;
        this.chatOwner = chatOwner;
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

    public Long getParticipantsNumber() {
        return participantsNumber;
    }

    public void setParticipantsNumber(Long participantsNumber) {
        this.participantsNumber = participantsNumber;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getChatOwner() {
        return chatOwner;
    }

    public void setChatOwner(String chatOwner) {
        this.chatOwner = chatOwner;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

}
