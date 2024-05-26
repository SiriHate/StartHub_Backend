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

@Service
public class EventsServiceImpl implements EventsService {

    final private EventRepository eventRepository;

    final private EventMapper eventMapper;

    @Autowired
    public EventsServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    @Transactional
    public void createEvent(EventFullRequest event) {
        Event eventEntity = eventMapper.toEvent(event);
        eventRepository.save(eventEntity);
    }

    @Override
    public EventFullResponse getEventById(Long id) {

        Optional<Event> event = eventRepository.findById(id);

        if (event.isEmpty()) {
            throw new NoSuchElementException("Event not found");
        }

        return eventMapper.toEventFullResponse(event.get());
    }

    @Override
    public List<EventSummaryResponse> getEventsByUsername(String username) {

        List<Event> eventList = eventRepository.findAll();

        if (eventList.isEmpty()) {
            throw new NoSuchElementException("No events found for username " + username);
        }

        return eventMapper.toEventSummaryResponseList(eventList);
    }

    @Override
    public List<EventSummaryResponse> getAllEvents() {

        List<Event> eventList = eventRepository.findAll();

        if (eventList.isEmpty()) {
            throw new NoSuchElementException("No events found");
        }

        return eventMapper.toEventSummaryResponseList(eventList);
    }

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
