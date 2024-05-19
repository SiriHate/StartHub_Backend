package org.siri_hate.chat_service.repository;

import org.siri_hate.chat_service.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
