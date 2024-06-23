package org.siri_hate.chat_service.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 * This class represents a request to create a new group chat.
 * It contains a chat title and a list of participants.
 */
public class CreateGroupChatRequest {

    /**
     * The title of the chat.
     * This field cannot be blank.
     */
    @NotBlank
    String chatTitle;

    /**
     * A list of participants in the chat.
     * The size of the list should be between 1 and 100.
     */
    @Size(min = 1, max = 100, message = "Group chat can contain from 1 to 100 users!")
    private List<String> participants;

    /**
     * Default constructor for the CreateGroupChatRequest class.
     */
    public CreateGroupChatRequest() { }

    /**
     * Constructor for the CreateGroupChatRequest class.
     * It initializes the chat title and the list of participants.
     *
     * @param chatTitle the title of the chat
     * @param participants the list of participants
     */
    public CreateGroupChatRequest(String chatTitle, List<String> participants) {
        this.chatTitle = chatTitle;
        this.participants = participants;
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

}