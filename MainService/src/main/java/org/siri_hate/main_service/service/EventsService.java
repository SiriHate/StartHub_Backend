package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.entity.Event;

import java.util.List;

public interface EventsService {

    Event createEvent(Event event);

    Event getEventById(Long id);

    List<Event> getEventsByUsername(String username);

    List<Event> getAllEvents();

    void updateEvent(Long id, Event eventDetails);

    void deleteEvent(Long id);

}
