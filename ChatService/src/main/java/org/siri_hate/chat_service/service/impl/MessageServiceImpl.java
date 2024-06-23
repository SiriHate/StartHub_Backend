package org.siri_hate.chat_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.chat_service.model.Message;
import org.siri_hate.chat_service.model.dto.mapper.MessageMapper;
import org.siri_hate.chat_service.model.dto.request.SendMessageRequest;
import org.siri_hate.chat_service.model.dto.response.message.MessageFullResponse;
import org.siri_hate.chat_service.repository.MessageRepository;
import org.siri_hate.chat_service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents the implementation of the MessageService interface.
 * It contains methods to send a message, get all messages by chat id, get messages by chat id with pagination, and find the latest message by chat id.
 */
@Service
public class MessageServiceImpl implements MessageService {

    /**
     * The message repository.
     */
    final private MessageRepository messageRepository;

    /**
     * The messaging template.
     */
    final private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * The message mapper.
     */
    final private MessageMapper messageMapper;

    /**
     * Constructor for the MessageServiceImpl class.
     * It initializes the message repository, messaging template, and message mapper.
     *
     * @param messageRepository the message repository
     * @param simpMessagingTemplate the messaging template
     * @param messageMapper the message mapper
     */
    @Autowired
    MessageServiceImpl(
            MessageRepository messageRepository,
            SimpMessagingTemplate simpMessagingTemplate,
            MessageMapper messageMapper
                      ) {
        this.messageRepository = messageRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageMapper = messageMapper;
    }

    /**
     * This method is used to send a message.
     * It maps the request to a message, sets the sender username and timestamp in the message, saves the message, and sends the message to the chat room.
     *
     * @param chatId the id of the chat
     * @param request the request to send a message
     * @param principal the principal of the sender
     */
    @Override
    @Transactional
    public void sendMessage(String chatId, SendMessageRequest request, Principal principal) {

        Message message = messageMapper.toMessage(request);
        message.setSenderUsername(principal.getName());
        message.setTimestamp(LocalDateTime.now());
        message.setChatId(chatId);

        Message savedMessage = messageRepository.save(message);

        simpMessagingTemplate.convertAndSend("/topic/chatRoom/" + chatId, savedMessage);
    }

    /**
     * This method is used to get all messages by chat id.
     * It returns all messages from the message repository.
     *
     * @param chatId the id of the chat
     * @return a list of all messages
     */
    @Override
    public List<Message> getAllMessagesByChatId(String chatId) {
        return messageRepository.findAllByChatId(chatId);
    }

    /**
     * This method is used to get messages by chat id with pagination.
     * It gets the messages from the message repository with pagination and maps them to message full responses.
     *
     * @param chatId the id of the chat
     * @param page the page number
     * @param size the page size
     * @return a list of message full responses
     */
    @Override
    public List<MessageFullResponse> getMessagesByChatIdWithPagination(String chatId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Message> messages = messageRepository.findByChatId(chatId, pageable);
        return messageMapper.toMessageFullResponseList(messages);
    }

    /**
     * This method is used to find the latest message by chat id.
     * It returns the latest message from the message repository.
     *
     * @param chatId the id of the chat
     * @return the latest message
     */
    @Override
    public Message findLatestMessageByChatId(String chatId) {
        return messageRepository.findLatestMessageByChatId(chatId);
    }

}