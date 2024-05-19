package org.siri_hate.chat_service.service;

import org.siri_hate.chat_service.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class ChatServiceImpl implements ChatService {

    final private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatServiceImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @Override
    public void sendMessage(ChatMessage message, Principal principal) {
        message.setNickname(principal.getName());
        message.setTimestamp(LocalDateTime.now());
        simpMessagingTemplate.convertAndSend("/topic/chat", message);
    }

}
