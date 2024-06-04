package org.siri_hate.chat_service.model.dto.request;

import jakarta.validation.constraints.Size;
import java.util.List;

public class CreatePersonalChatRequest {

    @Size(min = 2, max = 2, message = "Personal chat can contain only 2 participants!")
    private List<String> participants;

    public CreatePersonalChatRequest() { }

    public CreatePersonalChatRequest(List<String> participants) {
        this.participants = participants;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

}
