package org.siri_hate.chat_service.model;

import jakarta.persistence.*;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents a chat.
 * It contains the chat's id, title, type, list of participants, number of participants, and the chat owner.
 */
@Document(collection = "chats")
public class Chat {

    /**
     * The id of the chat.
     */
    @Id
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
     * The owner of the chat.
     */
    private String chatOwner;

    /**
     * Default constructor for the Chat class.
     */
    public Chat() { }

    /**
     * Constructor for the Chat class.
     * It initializes the id, title, type, list of participants, number of participants, and the chat owner.
     *
     * @param id the id of the chat
     * @param chatTitle the title of the chat
     * @param chatType the type of the chat
     * @param participants the list of participants
     * @param participantsNumber the number of participants
     * @param chatOwner the owner of the chat
     */
    public Chat(
            String id,
            String chatTitle,
            String chatType,
            List<String> participants,
            Long participantsNumber,
            String chatOwner
               ) {
        this.id = id;
        this.chatTitle = chatTitle;
        this.chatType = chatType;
        this.participants = participants;
        this.participantsNumber = participantsNumber;
        this.chatOwner = chatOwner;
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

}