package org.siri_hate.chat_service.service;

import org.siri_hate.chat_service.model.dto.request.CreatePersonalChatRequest;

public interface PersonalChatService {

    void createPersonalChat(CreatePersonalChatRequest request);

}
