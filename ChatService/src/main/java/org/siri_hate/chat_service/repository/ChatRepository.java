package org.siri_hate.chat_service.repository;

import org.siri_hate.chat_service.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * This interface represents a repository for the Chat model.
 * It extends the MongoRepository interface provided by Spring Data MongoDB.
 * It contains a method to find chats by participants.
 */
public interface ChatRepository extends MongoRepository<Chat, String> {

    /**
     * This method is used to find chats that contain a specific username in their participants list.
     *
     * @param username the username of the participant
     * @return a list of chats that contain the specified username in their participants list
     */
    List<Chat> findByParticipantsContaining(String username);
}