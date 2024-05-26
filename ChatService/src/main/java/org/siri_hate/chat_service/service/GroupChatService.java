package org.siri_hate.chat_service.service;

import org.siri_hate.chat_service.model.dto.request.CreateGroupChatRequest;

public interface GroupChatService {

    void createGroupChat(CreateGroupChatRequest request);

}
