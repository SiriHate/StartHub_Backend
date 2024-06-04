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

@RestController
@Validated
@RequestMapping("/api/v1/chat_service/chats")
public class ChatController {

    final private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/personal")
    public ResponseEntity<String> createPersonalChat(@RequestBody @Validated CreatePersonalChatRequest request) {
        chatService.createPersonalChat(request);
        return ResponseEntity.ok("Successfully created a personal chat");
    }

    @GetMapping("/personal/{id}")
    public ResponseEntity<PersonalChatFullResponse> getPersonalChatById(@PathVariable String id) {
        PersonalChatFullResponse chat = chatService.getPersonalChatById(id);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PostMapping("/group")
    public ResponseEntity<String> createGroupChat(@RequestBody @Validated CreateGroupChatRequest request) {
        chatService.createGroupChat(request);
        return ResponseEntity.ok("Successfully created a group chat");
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<GroupChatFullResponse> getGroupChatById(@PathVariable String id) {
        GroupChatFullResponse chat = chatService.getGroupChatById(id);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @GetMapping("/user/auth")
    public ResponseEntity<List<Chat>> getAllMyChats() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Chat> chats = chatService.getAllMyChats(username);
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonalChat(@PathVariable String id) {
        chatService.deleteChatById(id);
        return ResponseEntity.ok("Chat was successfully deleted!");
    }

}
