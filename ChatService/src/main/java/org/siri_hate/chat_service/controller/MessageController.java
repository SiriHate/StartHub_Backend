package org.siri_hate.chat_service.controller;

import org.siri_hate.chat_service.model.dto.request.SendMessageRequest;
import org.siri_hate.chat_service.model.dto.response.message.MessageFullResponse;
import org.siri_hate.chat_service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.siri_hate.chat_service.model.Message;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MessageController {

    final private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat/{id}")
    public ResponseEntity<String> sendMessage(
            @DestinationVariable String id,
            @Payload SendMessageRequest request,
            Principal principal
                                             ) {
        messageService.sendMessage(id, request, principal);
        return new ResponseEntity<>("Message was successfully sent!", HttpStatus.OK);
    }

    @GetMapping("/api/v1/messages/{chatId}")
    public ResponseEntity<List<MessageFullResponse>> getMessagesByChatIdWithPagination(String chatId, int page, int size) {
        List<MessageFullResponse> messages = messageService.getMessagesByChatIdWithPagination(chatId, page, size);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

}
