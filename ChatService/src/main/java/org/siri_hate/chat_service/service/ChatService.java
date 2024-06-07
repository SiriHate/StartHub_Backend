package org.siri_hate.chat_service.service;

import org.siri_hate.chat_service.model.Chat;
import org.siri_hate.chat_service.model.dto.request.CreateGroupChatRequest;
import org.siri_hate.chat_service.model.dto.request.CreatePersonalChatRequest;
import org.siri_hate.chat_service.model.dto.response.group_chat.GroupChatFullResponse;
import org.siri_hate.chat_service.model.dto.response.personal_chat.PersonalChatFullResponse;

import java.util.List;

public interface ChatService {

    void createPersonalChat(String creatorUsername, CreatePersonalChatRequest request);

    void createGroupChat(CreateGroupChatRequest request);

    List<Chat> getAllMyChats(String username);

    PersonalChatFullResponse getPersonalChatById(String id);

    GroupChatFullResponse getGroupChatById(String id);

    void deleteChatById(String chatId);

}
