package org.siri_hate.chat_service.service;

import org.siri_hate.chat_service.model.Message;
import org.siri_hate.chat_service.model.dto.request.SendMessageRequest;
import org.siri_hate.chat_service.model.dto.response.message.MessageFullResponse;

import java.security.Principal;
import java.util.List;

public interface MessageService {

    void sendMessage(String id, SendMessageRequest request, Principal principal);

    List<Message> getAllMessagesByChatId(String chatId);

    List<MessageFullResponse> getMessagesByChatIdWithPagination(String chatId, int page, int size);

    Message findLatestMessageByChatId(String chatId);

}
