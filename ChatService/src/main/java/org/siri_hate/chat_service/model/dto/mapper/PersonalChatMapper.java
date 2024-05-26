package org.siri_hate.chat_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.siri_hate.chat_service.model.PersonalChat;
import org.siri_hate.chat_service.model.dto.request.CreatePersonalChatRequest;

@Mapper(componentModel = "spring")
public interface PersonalChatMapper {

    PersonalChatMapper INSTANCE = Mappers.getMapper(PersonalChatMapper.class);

    PersonalChat toPersonalChatFromRequest(CreatePersonalChatRequest request);

}
