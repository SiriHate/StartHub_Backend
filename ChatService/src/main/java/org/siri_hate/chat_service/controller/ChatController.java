package org.siri_hate.chat_service.controller;

import org.siri_hate.chat_service.model.Message;
import org.siri_hate.chat_service.model.Chat;
import org.siri_hate.chat_service.model.dto.request.CreatePersonalChatRequest;
import org.siri_hate.chat_service.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    final private ChatService chatService;

    @Autowired
    public ChatController(SimpMessagingTemplate simpMessagingTemplate, ChatService chatService) {
        this.messagingTemplate = simpMessagingTemplate;
        this.chatService = chatService;
    }

    @GetMapping("/api/v1/chats/user/{userId}")
    public List<Chat> getChatsForUser(@PathVariable Long userId) {
        return null;
    }

    @MessageMapping("")
    public void createChatRoom(CreatePersonalChatRequest request, Principal principal) {
        String senderUsername = principal.getName();
        String recipientUsername = request.getRecipient();
        chatService.createChatRoom(senderUsername, recipientUsername);
    }

    @MessageMapping("/chat/{roomId}")
    public void sendMessage(@DestinationVariable String roomId, @Payload Message chatMessage, Principal principal) {
        chatService.sendMessage(chatMessage, principal);
    }

}
