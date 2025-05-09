package org.siri_hate.chat_service.service;

import org.siri_hate.chat_service.exception.ChatAlreadyExistsException;
import org.siri_hate.chat_service.exception.ChatNotFoundException;
import org.siri_hate.chat_service.model.dto.request.ChatFullRequest;
import org.siri_hate.chat_service.model.entity.Chat;
import org.siri_hate.chat_service.model.entity.User;
import org.siri_hate.chat_service.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserService userService) {
        this.chatRepository = chatRepository;
    }


    @Transactional
    public void deleteChat(Long chatId) {
        if (!chatRepository.existsById(chatId)) {
            throw new ChatNotFoundException("Chat with id " + chatId + " not found");
        }
        chatRepository.deleteById(chatId);
    }

    public Chat getChatById(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException("Chat with id " + chatId + " not found"));
    }
} 