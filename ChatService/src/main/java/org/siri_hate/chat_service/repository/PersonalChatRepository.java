package org.siri_hate.chat_service.repository;

import org.siri_hate.chat_service.model.PersonalChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalChatRepository extends JpaRepository<PersonalChat, Long> {
}
