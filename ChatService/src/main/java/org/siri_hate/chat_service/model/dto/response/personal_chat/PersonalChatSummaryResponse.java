package org.siri_hate.chat_service.model.dto.response.personal_chat;

import java.util.List;

/**
 * This class represents a summary response for a personal chat.
 * It contains the chat's id, title, type, list of participants, and the last message.
 */
public class PersonalChatSummaryResponse {

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
     * The last message in the chat.
     */
    private String lastMessage;

    /**
     * Default constructor for the PersonalChatSummaryResponse class.
     */
    public PersonalChatSummaryResponse() { }

    /**
     * Constructor for the PersonalChatSummaryResponse class.
     * It initializes the id, title, type, list of participants, and the last message.
     *
     * @param id the id of the chat
     * @param chatTitle the title of the chat
     * @param chatType the type of the chat
     * @param participants the list of participants
     * @param lastMessage the last message in the chat
     */
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
     * Getter for the last message.
     *
     * @return the last message in the chat
     */
    public String getLastMessage() {
        return lastMessage;
    }

    /**
     * Setter for the last message.
     *
     * @param lastMessage the new last message in the chat
     */
    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

}