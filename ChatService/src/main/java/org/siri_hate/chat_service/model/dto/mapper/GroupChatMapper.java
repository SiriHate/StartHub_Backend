package org.siri_hate.chat_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.siri_hate.chat_service.model.GroupChat;
import org.siri_hate.chat_service.model.dto.request.CreateGroupChatRequest;

@Mapper(componentModel = "spring")
public interface GroupChatMapper {

    GroupChatMapper INSTANCE = Mappers.getMapper(GroupChatMapper.class);

    GroupChat toGroupChatFromRequest(CreateGroupChatRequest request);

}
