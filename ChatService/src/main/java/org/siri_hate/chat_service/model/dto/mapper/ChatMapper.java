package org.siri_hate.chat_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.siri_hate.chat_service.model.Chat;
import org.siri_hate.chat_service.model.dto.request.CreateGroupChatRequest;
import org.siri_hate.chat_service.model.dto.request.CreatePersonalChatRequest;

/**
 * This interface is a mapper for Chat objects.
 * It uses MapStruct to map DTOs to Chat objects.
 */
@Mapper(componentModel = "spring")
public interface ChatMapper {

    /**
     * An instance of the ChatMapper.
     */
    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    /**
     * This method maps a CreatePersonalChatRequest to a Chat object.
     * It sets the chatType to "personal".
     *
     * @param request the CreatePersonalChatRequest
     * @return a Chat object
     */
    @Mapping(target = "chatType", constant = "personal")
    Chat toPersonalChat(CreatePersonalChatRequest request);

    /**
     * This method maps a CreateGroupChatRequest to a Chat object.
     * It sets the chatType to "group".
     *
     * @param request the CreateGroupChatRequest
     * @return a Chat object
     */
    @Mapping(target = "chatType", constant = "group")
    Chat toGroupChat(CreateGroupChatRequest request);

}