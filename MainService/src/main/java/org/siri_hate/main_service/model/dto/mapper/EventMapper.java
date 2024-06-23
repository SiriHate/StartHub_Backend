package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.request.event.EventFullRequest;
import org.siri_hate.main_service.model.dto.response.event.EventFullResponse;
import org.siri_hate.main_service.model.dto.response.event.EventSummaryResponse;
import org.siri_hate.main_service.model.entity.Event;

import java.util.List;

/**
 * This interface represents a mapper for Event.
 * It uses MapStruct, a code generator that simplifies the implementation of mappings between Java bean types.
 */
@Mapper(componentModel = "spring")
public interface EventMapper {

    /**
     * An instance of the EventMapper.
     */
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    /**
     * Maps from EventFullRequest to Event.
     *
     * @param event the EventFullRequest object
     * @return the mapped Event object
     */
    Event toEvent(EventFullRequest event);

    /**
     * Maps from Event to EventFullResponse.
     *
     * @param event the Event object
     * @return the mapped EventFullResponse object
     */
    EventFullResponse toEventFullResponse(Event event);

    /**
     * Maps from Event to EventSummaryResponse.
     *
     * @param event the Event object
     * @return the mapped EventSummaryResponse object
     */
    EventSummaryResponse toEventSummaryResponse(Event event);

    /**
     * Maps from a list of Event to a list of EventSummaryResponse.
     *
     * @param events the list of Event objects
     * @return the mapped list of EventSummaryResponse objects
     */
    List<EventSummaryResponse> toEventSummaryResponseList(List<Event> events);

    /**
     * Updates an existing Event object from an EventFullRequest object.
     *
     * @param eventFullRequest the EventFullRequest object
     * @param event            the Event object to be updated
     * @return the updated Event object
     */
    Event eventUpdate(EventFullRequest eventFullRequest, @MappingTarget Event event);

}