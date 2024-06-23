package org.siri_hate.chat_service.model.dto.response.personal_chat;

import org.siri_hate.chat_service.model.Message;
import org.siri_hate.chat_service.model.User;
import java.util.List;

/**
 * This class represents a full response for a personal chat.
 * It contains the chat's id, title, type, list of participants, and list of messages.
 */
public class PersonalChatFullResponse {

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
    private List<User> participants;

    /**
     * A list of messages in the chat.
     */
    private List<Message> messages;

    /**
     * Default constructor for the PersonalChatFullResponse class.
     */
    public PersonalChatFullResponse() { }

    /**
     * Constructor for the PersonalChatFullResponse class.
     * It initializes the id, title, type, list of participants, and list of messages.
     *
     * @param id the id of the chat
     * @param chatTitle the title of the chat
     * @param chatType the type of the chat
     * @param participants the list of participants
     * @param messages the list of messages
     */
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
    public List<User> getParticipants() {
        return participants;
    }

    /**
     * Setter for the list of participants.
     *
     * @param participants the new list of participants
     */
    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    /**
     * Getter for the list of messages.
     *
     * @return the list of messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Setter for the list of messages.
     *
     * @param messages the new list of messages
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}