package org.siri_hate.chat_service.repository;

import org.siri_hate.chat_service.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChatRepository extends MongoRepository<Chat, String> {
    List<Chat> findByParticipantsContaining(String username);
}
