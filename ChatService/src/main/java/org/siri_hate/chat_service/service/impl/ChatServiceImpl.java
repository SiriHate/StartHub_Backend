package org.siri_hate.chat_service.service.impl;

import org.siri_hate.chat_service.model.Message;
import org.siri_hate.chat_service.model.Chat;
import org.siri_hate.chat_service.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    final private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatServiceImpl(
            SimpMessagingTemplate simpMessagingTemplate
    ) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public List<Chat> getChatsForUser(Long id) {

        return null;


    }

    @Override
    public void createChatRoom(String senderUsername, String recipientUsername) {

    }

    @Override
    public void sendMessage(Message message, Principal principal) {
        message.setSender(principal.getName());
        simpMessagingTemplate.convertAndSend("/topic/chatRoom/" + "576", message);
    }



}
