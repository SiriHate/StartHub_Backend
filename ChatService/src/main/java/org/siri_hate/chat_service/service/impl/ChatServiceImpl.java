package org.siri_hate.chat_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.chat_service.exception.NoSuchChatException;
import org.siri_hate.chat_service.model.Chat;
import org.siri_hate.chat_service.model.Message;
import org.siri_hate.chat_service.model.User;
import org.siri_hate.chat_service.model.dto.mapper.ChatMapper;
import org.siri_hate.chat_service.model.dto.request.CreateGroupChatRequest;
import org.siri_hate.chat_service.model.dto.request.CreatePersonalChatRequest;
import org.siri_hate.chat_service.model.dto.response.group_chat.GroupChatFullResponse;
import org.siri_hate.chat_service.model.dto.response.personal_chat.PersonalChatFullResponse;
import org.siri_hate.chat_service.repository.ChatRepository;
import org.siri_hate.chat_service.service.ChatService;
import org.siri_hate.chat_service.service.MessageService;
import org.siri_hate.chat_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    final private ChatRepository chatRepository;

    final private ChatMapper chatMapper;

    final private UserService userService;

    final private MessageService messageService;

    @Autowired
    public ChatServiceImpl(
            ChatRepository chatRepository,
            ChatMapper chatMapper,
            @Lazy UserService userService,
            @Lazy MessageService messageService
                          ) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
        this.userService = userService;
        this.messageService = messageService;
    }

    @Override
    @Transactional
    public void createPersonalChat(String creatorUsername, CreatePersonalChatRequest request) {

        List<String> participants = Arrays.asList(creatorUsername, request.getRecipient());

        List<User> users = userService.findOrCreateUsers(participants);
        List<String> userIds = users.stream().map(User::getId).collect(Collectors.toList());

        Chat chat = chatMapper.toPersonalChat(request);
        chat.setParticipants(userIds);

        chatRepository.save(chat);
    }

    @Override
    @Transactional
    public void createGroupChat(CreateGroupChatRequest request) {

        List<User> users = userService.findOrCreateUsers(request.getParticipants());
        List<String> userIds = users.stream().map(User::getId).collect(Collectors.toList());

        Chat chat = chatMapper.toGroupChat(request);
        chat.setParticipants(userIds);
        chat.setParticipantsNumber((long) userIds.size());

        chatRepository.save(chat);
    }

    @Override
    public List<Chat> getAllMyChats(String username) {
        return chatRepository.findAll();
    }

    @Override
    public PersonalChatFullResponse getPersonalChatById(String id) {

        Chat chat = chatRepository.findById(id).orElseThrow(() -> new RuntimeException("Chat not found"));
        List<User> participants = userService.getUsersByIds(chat.getParticipants());
        List<Message> messages = messageService.getAllMessagesByChatId(chat.getId());

        return new PersonalChatFullResponse(
                chat.getId(),
                chat.getChatTitle(),
                chat.getChatType(),
                participants,
                messages
        );

    }

    @Override
    public GroupChatFullResponse getGroupChatById(String id) {

        Chat chat = chatRepository.findById(id).orElseThrow(() -> new RuntimeException("Chat not found"));

        List<User> participants = userService.getUsersByIds(chat.getParticipants());
        List<Message> messages = messageService.getAllMessagesByChatId(chat.getId());

        return new GroupChatFullResponse(
                chat.getId(),
                chat.getChatTitle(),
                chat.getChatType(),
                participants,
                chat.getParticipantsNumber(),
                messages,
                chat.getChatOwner()
        );

    }

    @Override
    public void deleteChatById(String chatId) {
        chatRepository.findById(chatId).orElseThrow(() -> new NoSuchChatException("Chat with id: " + chatId + " not found!"));
        chatRepository.deleteById(chatId);
    }

}
