package org.siri_hate.chat_service.service;

import org.siri_hate.chat_service.model.Message;
import org.siri_hate.chat_service.model.Chat;

import java.security.Principal;
import java.util.List;

public interface ChatService {

    List<Chat> getChatsForUser(Long id);

    void createChatRoom(String senderUsername, String recipientUsername);

    void sendMessage(Message message, Principal principal);

}
