package org.siri_hate.main_service.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.siri_hate.main_service.model.dto.request.event.EventFullRequest;
import org.siri_hate.main_service.model.dto.response.event.EventFullResponse;
import org.siri_hate.main_service.model.dto.response.event.EventSummaryResponse;
import org.siri_hate.main_service.model.entity.Event;
import org.siri_hate.main_service.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/main_service/events")
public class EventsController {

  private final EventsService eventsService;

  @Autowired
  public EventsController(EventsService eventsService) {
    this.eventsService = eventsService;
  }

  @PostMapping
  public ResponseEntity<String> createEvent(@Valid @RequestBody EventFullRequest event) {
    eventsService.createEvent(event);
    return new ResponseEntity<>("Event was successfully created", HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EventFullResponse> getEventById(@PathVariable Long id) {
    EventFullResponse event = eventsService.getEventById(id);
    return new ResponseEntity<>(event, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<EventSummaryResponse>> getAllEvents() {
    List<EventSummaryResponse> events = eventsService.getAllEvents();
    return new ResponseEntity<>(events, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateEvent(
      @PathVariable Long id, @Valid @RequestBody Event eventDetails) {
    eventsService.updateEvent(id, eventDetails);
    return new ResponseEntity<>("Event has been successfully modified", HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
    eventsService.deleteEvent(id);
    return new ResponseEntity<>("Event has been successfully deleted", HttpStatus.NO_CONTENT);
  }
}
