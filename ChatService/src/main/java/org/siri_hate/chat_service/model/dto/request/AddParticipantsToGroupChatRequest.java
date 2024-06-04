package org.siri_hate.chat_service.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import org.siri_hate.chat_service.model.User;

import java.util.Set;

public class AddParticipantsToGroupChatRequest {

    @NotEmpty
    Set<User> newParticipants;

}
