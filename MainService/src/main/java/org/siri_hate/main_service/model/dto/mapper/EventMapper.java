package org.siri_hate.main_service.model.dto.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.request.event.EventFullRequest;
import org.siri_hate.main_service.model.dto.response.event.EventFullResponse;
import org.siri_hate.main_service.model.dto.response.event.EventSummaryResponse;
import org.siri_hate.main_service.model.entity.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {

  EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

  Event toEvent(EventFullRequest event);

  EventFullResponse toEventFullResponse(Event event);

  EventSummaryResponse toEventSummaryResponse(Event event);

  List<EventSummaryResponse> toEventSummaryResponseList(List<Event> events);

  Event eventUpdate(EventFullRequest eventFullRequest, @MappingTarget Event event);
}
