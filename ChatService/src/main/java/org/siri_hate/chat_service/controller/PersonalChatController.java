package org.siri_hate.chat_service.controller;

import org.siri_hate.chat_service.model.dto.request.CreatePersonalChatRequest;
import org.siri_hate.chat_service.service.PersonalChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/chat_service/personal_chats")
@Validated
public class PersonalChatController {

    final private PersonalChatService personalChatService;

    @Autowired
    public PersonalChatController(PersonalChatService personalChatService) {
        this.personalChatService = personalChatService;
    }

    @PostMapping
    public ResponseEntity<String> createPersonalChat(@Validated @RequestBody CreatePersonalChatRequest request) {

    }

}
