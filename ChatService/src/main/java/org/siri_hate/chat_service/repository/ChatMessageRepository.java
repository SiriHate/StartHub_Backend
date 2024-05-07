package org.siri_hate.chat_service.repository;


import org.siri_hate.chat_service.model.ChatMessage;
import org.siri_hate.chat_service.model.enums.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.List;

public interface ChatMessageRepository
        extends MongoRepository<ChatMessage, String> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);
}
