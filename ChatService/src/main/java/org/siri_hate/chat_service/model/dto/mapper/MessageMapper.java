package org.siri_hate.chat_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.siri_hate.chat_service.model.Message;
import org.siri_hate.chat_service.model.dto.request.SendMessageRequest;
import org.siri_hate.chat_service.model.dto.response.message.MessageFullResponse;
import java.util.List;

/**
 * This interface is a mapper for Message objects.
 * It uses MapStruct to map DTOs to Message objects and vice versa.
 */
@Mapper(componentModel = "spring")
public interface MessageMapper {

    /**
     * An instance of the MessageMapper.
     */
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    /**
     * This method maps a SendMessageRequest to a Message object.
     *
     * @param request the SendMessageRequest
     * @return a Message object
     */
    Message toMessage(SendMessageRequest request);

    /**
     * This method maps a list of Message objects to a list of MessageFullResponse objects.
     *
     * @param messages the list of Message objects
     * @return a list of MessageFullResponse objects
     */
    List<MessageFullResponse> toMessageFullResponseList(List<Message> messages);

}