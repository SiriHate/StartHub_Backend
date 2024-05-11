package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
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

    @Autowired
    public EventsServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event getEventById(Long id) {

        Optional<Event> event = eventRepository.findById(id);

        if (event.isEmpty()) {
            throw new NoSuchElementException("Event not found");
        }

        return event.get();
    }

    @Override
    public List<Event> getEventsByUsername(String username) {

        List<Event> eventList = eventRepository.findAll();

        if (eventList.isEmpty()) {
            throw new NoSuchElementException("No events found for username " + username);
        }

        return eventList;
    }

    @Override
    public List<Event> getAllEvents() {

        List<Event> eventList = eventRepository.findAll();

        if (eventList.isEmpty()) {
            throw new NoSuchElementException("No events found");
        }

        return eventList;
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
