package org.siri_hate.chat_service.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import org.siri_hate.chat_service.model.User;

import java.util.Set;

/**
 * This class represents a request to add participants to a group chat.
 * It contains a set of users that are to be added as new participants.
 */
public class AddParticipantsToGroupChatRequest {

    /**
     * A set of users that are to be added as new participants to a group chat.
     * This field cannot be empty.
     */
    @NotEmpty
    Set<User> newParticipants;

}