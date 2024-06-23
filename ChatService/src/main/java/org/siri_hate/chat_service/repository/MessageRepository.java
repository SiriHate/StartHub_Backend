package org.siri_hate.chat_service.repository;

import org.siri_hate.chat_service.model.Message;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * This interface represents a repository for the Message model.
 * It extends the MongoRepository interface provided by Spring Data MongoDB.
 * It contains methods to find all messages by chat id, find messages by chat id with pagination, and find the latest message by chat id.
 */
public interface MessageRepository extends MongoRepository<Message, String> {

    /**
     * This method is used to find all messages that belong to a specific chat.
     *
     * @param chatId the id of the chat
     * @return a list of messages that belong to the specified chat
     */
    List<Message> findAllByChatId(String chatId);

    /**
     * This method is used to find messages that belong to a specific chat with pagination.
     *
     * @param chatId the id of the chat
     * @param pageable the pagination information
     * @return a list of messages that belong to the specified chat
     */
    List<Message> findByChatId(String chatId, Pageable pageable);

    /**
     * This method is used to find the latest message that belongs to a specific chat.
     * It uses the findByChatId method with pagination to get the latest message.
     *
     * @param chatId the id of the chat
     * @return the latest message that belongs to the specified chat, or null if there are no messages
     */
    default Message findLatestMessageByChatId(String chatId) {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "timestamp"));
        List<Message> messages = findByChatId(chatId, pageable);
        return messages.isEmpty() ? null : messages.get(0);
    }

}