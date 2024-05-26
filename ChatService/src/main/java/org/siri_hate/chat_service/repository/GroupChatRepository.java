package org.siri_hate.chat_service.repository;

import org.siri_hate.chat_service.model.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {
}
