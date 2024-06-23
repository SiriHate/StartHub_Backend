package org.siri_hate.chat_service.service;

import org.siri_hate.chat_service.model.Chat;
import org.siri_hate.chat_service.model.dto.request.CreateGroupChatRequest;
import org.siri_hate.chat_service.model.dto.request.CreatePersonalChatRequest;
import org.siri_hate.chat_service.model.dto.response.group_chat.GroupChatFullResponse;
import org.siri_hate.chat_service.model.dto.response.personal_chat.PersonalChatFullResponse;

import java.util.List;

/**
 * This interface represents the service for chat operations.
 * It contains methods to create personal and group chats, get all chats for a user, get a personal or group chat by id, and delete a chat by id.
 */
public interface ChatService {

    /**
     * This method is used to create a personal chat.
     *
     * @param creatorUsername the username of the chat creator
     * @param request the request to create a personal chat
     */
    void createPersonalChat(String creatorUsername, CreatePersonalChatRequest request);

    /**
     * This method is used to create a group chat.
     *
     * @param request the request to create a group chat
     */
    void createGroupChat(CreateGroupChatRequest request);

    /**
     * This method is used to get all chats for a user.
     *
     * @param username the username of the user
     * @return a list of all chats
     */
    List<Chat> getAllMyChats(String username);

    /**
     * This method is used to get a personal chat by id.
     *
     * @param id the id of the chat
     * @return a personal chat full response
     */
    PersonalChatFullResponse getPersonalChatById(String id);

    /**
     * This method is used to get a group chat by id.
     *
     * @param id the id of the chat
     * @return a group chat full response
     */
    GroupChatFullResponse getGroupChatById(String id);

    /**
     * This method is used to delete a chat by id.
     *
     * @param chatId the id of the chat
     */
    void deleteChatById(String chatId);

}