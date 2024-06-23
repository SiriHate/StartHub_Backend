package org.siri_hate.chat_service.model.dto.response.group_chat;

import org.siri_hate.chat_service.model.Message;
import java.util.List;

/**
 * This class represents a summary response for a group chat.
 * It contains the chat's id, title, type, list of participants, number of participants, list of messages, the chat owner, and the last message.
 */
public class GroupChatSummaryResponse {

    /**
     * The id of the chat.
     */
    private String id;

    /**
     * The title of the chat.
     */
    private String chatTitle;

    /**
     * The type of the chat.
     */
    private String chatType;

    /**
     * A list of participants in the chat.
     */
    private List<String> participants;

    /**
     * The number of participants in the chat.
     */
    private Long participantsNumber;

    /**
     * A list of messages in the chat.
     */
    private List<String> messages;

    /**
     * The owner of the chat.
     */
    private String chatOwner;

    /**
     * The last message in the chat.
     */
    private Message lastMessage;

    /**
     * Default constructor for the GroupChatSummaryResponse class.
     */
    public GroupChatSummaryResponse() { }

    /**
     * Constructor for the GroupChatSummaryResponse class.
     * It initializes the id, title, type, list of participants, number of participants, list of messages, the chat owner, and the last message.
     *
     * @param id the id of the chat
     * @param chatTitle the title of the chat
     * @param chatType the type of the chat
     * @param participants the list of participants
     * @param participantsNumber the number of participants
     * @param messages the list of messages
     * @param chatOwner the owner of the chat
     * @param lastMessage the last message in the chat
     */
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

    /**
     * Getter for the id.
     *
     * @return the id of the chat
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the id.
     *
     * @param id the new id of the chat
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the chat title.
     *
     * @return the title of the chat
     */
    public String getChatTitle() {
        return chatTitle;
    }

    /**
     * Setter for the chat title.
     *
     * @param chatTitle the new title of the chat
     */
    public void setChatTitle(String chatTitle) {
        this.chatTitle = chatTitle;
    }

    /**
     * Getter for the chat type.
     *
     * @return the type of the chat
     */
    public String getChatType() {
        return chatType;
    }

    /**
     * Setter for the chat type.
     *
     * @param chatType the new type of the chat
     */
    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    /**
     * Getter for the list of participants.
     *
     * @return the list of participants
     */
    public List<String> getParticipants() {
        return participants;
    }

    /**
     * Setter for the list of participants.
     *
     * @param participants the new list of participants
     */
    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    /**
     * Getter for the number of participants.
     *
     * @return the number of participants
     */
    public Long getParticipantsNumber() {
        return participantsNumber;
    }

    /**
     * Setter for the number of participants.
     *
     * @param participantsNumber the new number of participants
     */
    public void setParticipantsNumber(Long participantsNumber) {
        this.participantsNumber = participantsNumber;
    }

    /**
     * Getter for the list of messages.
     *
     * @return the list of messages
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     * Setter for the list of messages.
     *
     * @param messages the new list of messages
     */
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    /**
     * Getter for the chat owner.
     *
     * @return the owner of the chat
     */
    public String getChatOwner() {
        return chatOwner;
    }

    /**
     * Setter for the chat owner.
     *
     * @param chatOwner the new owner of the chat
     */
    public void setChatOwner(String chatOwner) {
        this.chatOwner = chatOwner;
    }

    /**
     * Getter for the last message.
     *
     * @return the last message in the chat
     */
    public Message getLastMessage() {
        return lastMessage;
    }

    /**
     * Setter for the last message.
     *
     * @param lastMessage the new last message in the chat
     */
    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

}