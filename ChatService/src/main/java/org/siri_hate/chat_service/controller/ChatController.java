package org.siri_hate.chat_service.controller;

import org.siri_hate.chat_service.model.Chat;
import org.siri_hate.chat_service.model.dto.request.CreateGroupChatRequest;
import org.siri_hate.chat_service.model.dto.request.CreatePersonalChatRequest;
import org.siri_hate.chat_service.model.dto.response.group_chat.GroupChatFullResponse;
import org.siri_hate.chat_service.model.dto.response.personal_chat.PersonalChatFullResponse;
import org.siri_hate.chat_service.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller for chat-related operations.
 * It handles HTTP requests related to personal and group chats.
 */
@RestController
@Validated
@RequestMapping("/api/v1/chat_service/chats")
public class ChatController {

    final private ChatService chatService;

    /**
     * Constructor for the ChatController class.
     * It initializes the ChatService.
     *
     * @param chatService the chat service
     */
    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * This method handles POST requests to create a personal chat.
     * It uses the authenticated user's username and the request body to create a personal chat.
     *
     * @param request the request body containing the details of the personal chat to be created
     * @return a ResponseEntity with a success message and HTTP status code
     */
    @PostMapping("/personal")
    public ResponseEntity<String> createPersonalChat(@RequestBody @Validated CreatePersonalChatRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        chatService.createPersonalChat(username, request);
        return new ResponseEntity<>("Successfully created a personal chat", HttpStatus.CREATED);
    }

    /**
     * This method handles GET requests to retrieve a personal chat by its ID.
     *
     * @param id the ID of the personal chat to be retrieved
     * @return a ResponseEntity with the retrieved personal chat and HTTP status code
     */
    @GetMapping("/personal/{id}")
    public ResponseEntity<PersonalChatFullResponse> getPersonalChatById(@PathVariable String id) {
        PersonalChatFullResponse chat = chatService.getPersonalChatById(id);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    /**
     * This method handles POST requests to create a group chat.
     * It uses the request body to create a group chat.
     *
     * @param request the request body containing the details of the group chat to be created
     * @return a ResponseEntity with a success message and HTTP status code
     */
    @PostMapping("/group")
    public ResponseEntity<String> createGroupChat(@RequestBody @Validated CreateGroupChatRequest request) {
        chatService.createGroupChat(request);
        return new ResponseEntity<>("Successfully created a group chat", HttpStatus.CREATED);
    }

    /**
     * This method handles GET requests to retrieve a group chat by its ID.
     *
     * @param id the ID of the group chat to be retrieved
     * @return a ResponseEntity with the retrieved group chat and HTTP status code
     */
    @GetMapping("/group/{id}")
    public ResponseEntity<GroupChatFullResponse> getGroupChatById(@PathVariable String id) {
        GroupChatFullResponse chat = chatService.getGroupChatById(id);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    /**
     * This method handles GET requests to retrieve all chats of the authenticated user.
     *
     * @return a ResponseEntity with the retrieved chats and HTTP status code
     */
    @GetMapping("/user/auth")
    public ResponseEntity<List<Chat>> getAllMyChats() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Chat> chats = chatService.getAllMyChats(username);
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    /**
     * This method handles DELETE requests to delete a chat by its ID.
     *
     * @param id the ID of the chat to be deleted
     * @return a ResponseEntity with a success message and HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonalChat(@PathVariable String id) {
        chatService.deleteChatById(id);
        return ResponseEntity.ok("Chat was successfully deleted!");
    }

}