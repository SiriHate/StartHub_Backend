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

@Service
public class MessageServiceImpl implements MessageService {

    final private MessageRepository messageRepository;

    final private SimpMessagingTemplate simpMessagingTemplate;

    final private MessageMapper messageMapper;

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

    @Override
    public List<Message> getAllMessagesByChatId(String chatId) {
        return messageRepository.findAllByChatId(chatId);
    }

    @Override
    public List<MessageFullResponse> getMessagesByChatIdWithPagination(String chatId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Message> messages = messageRepository.findByChatId(chatId, pageable);
        return messageMapper.toMessageFullResponseList(messages);
    }

    @Override
    public Message findLatestMessageByChatId(String chatId) {
        return messageRepository.findLatestMessageByChatId(chatId);
    }

}
