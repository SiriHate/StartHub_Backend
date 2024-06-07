package org.siri_hate.chat_service.repository;

import org.siri_hate.chat_service.model.Message;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findAllByChatId(String chatId);

    List<Message> findByChatId(String chatId, Pageable pageable);

    default Message findLatestMessageByChatId(String chatId) {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "timestamp"));
        List<Message> messages = findByChatId(chatId, pageable);
        return messages.isEmpty() ? null : messages.get(0);
    }

}
