package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.event.EventFullRequest;
import org.siri_hate.main_service.model.dto.response.event.EventFullResponse;
import org.siri_hate.main_service.model.dto.response.event.EventSummaryResponse;
import org.siri_hate.main_service.model.entity.Event;

import java.util.List;

/**
 * This interface defines the contract for the EventsService.
 * It provides methods for creating, retrieving, updating, and deleting Event entities.
 */
public interface EventsService {

    /**
     * This method creates a new Event entity from a request DTO and saves it in the database.
     *
     * @param event the EventFullRequest DTO containing the data for the new Event.
     */
    void createEvent(EventFullRequest event);

    /**
     * This method retrieves an Event entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the Event to retrieve.
     * @return an EventFullResponse DTO.
     */
    EventFullResponse getEventById(Long id);

    /**
     * This method retrieves all Event entities and converts them to summary response DTOs.
     *
     * @return a List of EventSummaryResponse DTOs.
     */
    List<EventSummaryResponse> getAllEvents();

    /**
     * This method updates an existing Event entity with data from a provided Event entity and saves it in the database.
     *
     * @param id           the ID of the Event to update.
     * @param eventDetails the Event containing the new data for the Event.
     */
    void updateEvent(Long id, Event eventDetails);

    /**
     * This method deletes an Event entity by its ID from the database.
     *
     * @param id the ID of the Event to delete.
     */
    void deleteEvent(Long id);

}