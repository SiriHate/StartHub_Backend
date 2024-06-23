package org.siri_hate.chat_service.service;

import org.siri_hate.chat_service.model.Message;
import org.siri_hate.chat_service.model.dto.request.SendMessageRequest;
import org.siri_hate.chat_service.model.dto.response.message.MessageFullResponse;

import java.security.Principal;
import java.util.List;

/**
 * This interface represents the service for message operations.
 * It contains methods to send a message, get all messages by chat id, get messages by chat id with pagination, and find the latest message by chat id.
 */
public interface MessageService {

    /**
     * This method is used to send a message.
     *
     * @param id the id of the chat
     * @param request the request to send a message
     * @param principal the principal of the sender
     */
    void sendMessage(String id, SendMessageRequest request, Principal principal);

    /**
     * This method is used to get all messages by chat id.
     *
     * @param chatId the id of the chat
     * @return a list of all messages
     */
    List<Message> getAllMessagesByChatId(String chatId);

    /**
     * This method is used to get messages by chat id with pagination.
     *
     * @param chatId the id of the chat
     * @param page the page number
     * @param size the page size
     * @return a list of message full responses
     */
    List<MessageFullResponse> getMessagesByChatIdWithPagination(String chatId, int page, int size);

    /**
     * This method is used to find the latest message by chat id.
     *
     * @param chatId the id of the chat
     * @return the latest message
     */
    Message findLatestMessageByChatId(String chatId);

}