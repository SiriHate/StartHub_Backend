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

/**
 * This class represents the implementation of the ChatService interface.
 * It contains methods to create personal and group chats, get all chats for a user, get a personal or group chat by id, and delete a chat by id.
 */
@Service
public class ChatServiceImpl implements ChatService {

    /**
     * The chat repository.
     */
    final private ChatRepository chatRepository;

    /**
     * The chat mapper.
     */
    final private ChatMapper chatMapper;

    /**
     * The user service.
     */
    final private UserService userService;

    /**
     * The message service.
     */
    final private MessageService messageService;

    /**
     * Constructor for the ChatServiceImpl class.
     * It initializes the chat repository, chat mapper, user service, and message service.
     *
     * @param chatRepository the chat repository
     * @param chatMapper the chat mapper
     * @param userService the user service
     * @param messageService the message service
     */
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

    /**
     * This method is used to create a personal chat.
     * It finds or creates users for the participants, maps the request to a chat, sets the participants in the chat, and saves the chat.
     *
     * @param creatorUsername the username of the chat creator
     * @param request the request to create a personal chat
     */
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

    /**
     * This method is used to create a group chat.
     * It finds or creates users for the participants, maps the request to a chat, sets the participants and participants number in the chat, and saves the chat.
     *
     * @param request the request to create a group chat
     */
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

    /**
     * This method is used to get all chats for a user.
     * It returns all chats from the chat repository.
     *
     * @param username the username of the user
     * @return a list of all chats
     */
    @Override
    public List<Chat> getAllMyChats(String username) {
        return chatRepository.findAll();
    }

    /**
     * This method is used to get a personal chat by id.
     * It finds the chat by id, gets the users and messages for the chat, and returns a personal chat full response.
     *
     * @param id the id of the chat
     * @return a personal chat full response
     */
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

    /**
     * This method is used to get a group chat by id.
     * It finds the chat by id, gets the users and messages for the chat, and returns a group chat full response.
     *
     * @param id the id of the chat
     * @return a group chat full response
     */
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

    /**
     * This method is used to delete a chat by id.
     * It finds the chat by id and deletes it.
     *
     * @param chatId the id of the chat
     */
    @Override
    public void deleteChatById(String chatId) {
        chatRepository.findById(chatId).orElseThrow(() -> new NoSuchChatException("Chat with id: " + chatId + " not found!"));
        chatRepository.deleteById(chatId);
    }

}