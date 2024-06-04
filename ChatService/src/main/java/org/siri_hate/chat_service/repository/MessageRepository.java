package org.siri_hate.chat_service.repository;

import org.siri_hate.chat_service.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findAllByChatId(String chatId);
    List<Message> findByChatId(String chatId, Pageable pageable);
}
