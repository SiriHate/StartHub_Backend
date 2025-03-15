package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import org.siri_hate.main_service.model.dto.mapper.EventMapper;
import org.siri_hate.main_service.model.dto.request.event.EventFullRequest;
import org.siri_hate.main_service.model.dto.response.event.EventFullResponse;
import org.siri_hate.main_service.model.dto.response.event.EventSummaryResponse;
import org.siri_hate.main_service.model.entity.Event;
import org.siri_hate.main_service.repository.EventRepository;
import org.siri_hate.main_service.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsServiceImpl implements EventsService {

  private final EventRepository eventRepository;
  private final EventMapper eventMapper;

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
    return eventMapper.toEventFullResponse(
        eventRepository
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("Event not found")));
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
    eventRepository
        .findById(id)
        .orElseThrow(() -> new NoSuchElementException("Event with id = " + id + " not found"));
    eventDetails.setId(id);
    eventRepository.save(eventDetails);
  }

  @Override
  @Transactional
  public void deleteEvent(Long id) {
    Event event =
        eventRepository
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("Event with id = " + id + " not found"));
    eventRepository.delete(event);
  }
}
