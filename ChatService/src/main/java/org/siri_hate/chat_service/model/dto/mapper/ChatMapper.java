package org.siri_hate.chat_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.siri_hate.chat_service.model.Chat;
import org.siri_hate.chat_service.model.dto.request.CreateGroupChatRequest;
import org.siri_hate.chat_service.model.dto.request.CreatePersonalChatRequest;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    @Mapping(target = "chatType", constant = "personal")
    Chat toPersonalChat(CreatePersonalChatRequest request);

    @Mapping(target = "chatType", constant = "group")
    Chat toGroupChat(CreateGroupChatRequest request);

}
