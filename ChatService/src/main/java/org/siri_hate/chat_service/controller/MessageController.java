package org.siri_hate.chat_service.controller;

import org.siri_hate.chat_service.model.dto.request.SendMessageRequest;
import org.siri_hate.chat_service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * This class is a controller for message-related operations.
 * It handles WebSocket messages related to sending messages in a chat.
 */
@Controller
public class MessageController {

    final private MessageService messageService;

    /**
     * Constructor for the MessageController class.
     * It initializes the MessageService.
     *
     * @param messageService the message service
     */
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * This method handles WebSocket messages to send a message in a chat.
     * It uses the chat ID from the message destination, the message content from the payload, and the sender from the Principal.
     *
     * @param id the ID of the chat to send the message in
     * @param request the request body containing the content of the message to be sent
     * @param principal the Principal representing the sender of the message
     * @return a ResponseEntity with a success message and HTTP status code
     */
    @MessageMapping("/chat/{id}")
    public ResponseEntity<String> sendMessage(
            @DestinationVariable String id,
            @Payload SendMessageRequest request,
            Principal principal
                                             ) {
        messageService.sendMessage(id, request, principal);
        return new ResponseEntity<>("Message was successfully sent!", HttpStatus.OK);
    }

}