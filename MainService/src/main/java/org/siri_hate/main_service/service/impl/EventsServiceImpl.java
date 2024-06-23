package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.model.dto.mapper.EventMapper;
import org.siri_hate.main_service.model.dto.request.event.EventFullRequest;
import org.siri_hate.main_service.model.dto.response.event.EventFullResponse;
import org.siri_hate.main_service.model.dto.response.event.EventSummaryResponse;
import org.siri_hate.main_service.model.entity.Event;
import org.siri_hate.main_service.repository.EventRepository;
import org.siri_hate.main_service.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class implements the EventsService interface.
 * It provides methods for creating, retrieving, updating, and deleting Event entities.
 * It is annotated with @Service, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Service
public class EventsServiceImpl implements EventsService {

    final private EventRepository eventRepository;

    final private EventMapper eventMapper;

    /**
     * Constructor for EventsServiceImpl.
     *
     * @param eventRepository the EventRepository to use for database operations.
     * @param eventMapper     the EventMapper to use for converting between DTOs and entities.
     */
    @Autowired
    public EventsServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    /**
     * This method creates a new Event entity from a request DTO and saves it in the database.
     *
     * @param event the EventFullRequest DTO containing the data for the new Event.
     */
    @Override
    @Transactional
    public void createEvent(EventFullRequest event) {
        Event eventEntity = eventMapper.toEvent(event);
        eventRepository.save(eventEntity);
    }

    /**
     * This method retrieves an Event entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the Event to retrieve.
     * @return an EventFullResponse DTO.
     */
    @Override
    public EventFullResponse getEventById(Long id) {

        Optional<Event> event = eventRepository.findById(id);

        if (event.isEmpty()) {
            throw new NoSuchElementException("Event not found");
        }

        return eventMapper.toEventFullResponse(event.get());
    }

    /**
     * This method retrieves all Event entities and converts them to summary response DTOs.
     *
     * @return a List of EventSummaryResponse DTOs.
     */
    @Override
    public List<EventSummaryResponse> getAllEvents() {

        List<Event> eventList = eventRepository.findAll();

        if (eventList.isEmpty()) {
            throw new NoSuchElementException("No events found");
        }

        return eventMapper.toEventSummaryResponseList(eventList);
    }

    /**
     * This method updates an existing Event entity with data from a provided Event entity and saves it in the database.
     *
     * @param id           the ID of the Event to update.
     * @param eventDetails the Event containing the new data for the Event.
     */
    @Override
    @Transactional
    public void updateEvent(Long id, Event eventDetails) {

        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new NoSuchElementException("Event with id = " + id + " not found");
        }

        eventDetails.setId(id);
        eventRepository.save(eventDetails);
    }

    /**
     * This method deletes an Event entity by its ID from the database.
     *
     * @param id the ID of the Event to delete.
     */
    @Override
    @Transactional
    public void deleteEvent(Long id) {

        Optional<Event> event = eventRepository.findById(id);

        if (event.isEmpty()) {
            throw new NoSuchElementException("Event with id = " + id + " not found");
        }

        eventRepository.delete(event.get());
    }
}