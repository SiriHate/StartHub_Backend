package org.siri_hate.main_service.controller;

import jakarta.validation.Valid;
import org.siri_hate.main_service.model.entity.Event;
import org.siri_hate.main_service.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/main/events")
public class EventsController {

    final private EventsService eventsService;

    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        Event createdEvent = eventsService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventsService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<Event>> getEventsByUsername(@PathVariable String username) {
        List<Event> events = eventsService.getEventsByUsername(username);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventsService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @Valid @RequestBody Event eventDetails) {
        eventsService.updateEvent(id, eventDetails);
        return new ResponseEntity<>("Event has been successfully modified", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventsService.deleteEvent(id);
        return new ResponseEntity<>("Event has been successfully deleted", HttpStatus.NO_CONTENT);
    }

}
