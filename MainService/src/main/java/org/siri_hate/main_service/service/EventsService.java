package org.siri_hate.main_service.service;

import java.util.List;
import org.siri_hate.main_service.model.dto.request.event.EventFullRequest;
import org.siri_hate.main_service.model.dto.response.event.EventFullResponse;
import org.siri_hate.main_service.model.dto.response.event.EventSummaryResponse;
import org.siri_hate.main_service.model.entity.Event;

public interface EventsService {

  void createEvent(EventFullRequest event);

  EventFullResponse getEventById(Long id);

  List<EventSummaryResponse> getAllEvents();

  void updateEvent(Long id, Event eventDetails);

  void deleteEvent(Long id);
}
