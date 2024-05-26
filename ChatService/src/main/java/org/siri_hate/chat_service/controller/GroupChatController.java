package org.siri_hate.chat_service.controller;

import org.siri_hate.chat_service.model.GroupChat;
import org.siri_hate.chat_service.model.dto.request.CreateGroupChatRequest;
import org.siri_hate.chat_service.service.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/chat_service/group_chats")
@Validated
public class GroupChatController {

    final private GroupChatService groupChatService;

    @Autowired
    public GroupChatController(GroupChatService groupChatService) {
        this.groupChatService = groupChatService;
    }

    @PostMapping
    public ResponseEntity<String> createGroupChat(CreateGroupChatRequest request) {
        GroupChat =
    }

}
