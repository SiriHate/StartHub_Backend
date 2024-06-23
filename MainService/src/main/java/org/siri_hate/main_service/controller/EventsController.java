package org.siri_hate.main_service.controller;

import jakarta.validation.Valid;
import org.siri_hate.main_service.model.dto.request.event.EventFullRequest;
import org.siri_hate.main_service.model.dto.response.event.EventFullResponse;
import org.siri_hate.main_service.model.dto.response.event.EventSummaryResponse;
import org.siri_hate.main_service.model.entity.Event;
import org.siri_hate.main_service.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller for handling requests related to Events.
 * It provides endpoints for creating, retrieving, updating, and deleting Events.
 */
@RestController
@Validated
@RequestMapping("/api/v1/main_service/events")
public class EventsController {

    final private EventsService eventsService;

    /**
     * Constructor for the EventsController class.
     * It initializes the EventsService.
     *
     * @param eventsService the service for handling business logic related to Events
     */
    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    /**
     * This method handles the creation of a new Event.
     *
     * @param event the request body containing the details of the new Event
     * @return a response entity with a success message and HTTP status code
     */
    @PostMapping
    public ResponseEntity<String> createEvent(@Valid @RequestBody EventFullRequest event) {
        eventsService.createEvent(event);
        return new ResponseEntity<>("Event was successfully created", HttpStatus.CREATED);
    }

    /**
     * This method retrieves an Event by its ID.
     *
     * @param id the ID of the Event to retrieve
     * @return a response entity with the requested Event and HTTP status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventFullResponse> getEventById(@PathVariable Long id) {
        EventFullResponse event = eventsService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    /**
     * This method retrieves all Events.
     *
     * @return a response entity with a list of all Events and HTTP status code
     */
    @GetMapping
    public ResponseEntity<List<EventSummaryResponse>> getAllEvents() {
        List<EventSummaryResponse> events = eventsService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    /**
     * This method updates an existing Event.
     *
     * @param id           the ID of the Event to update
     * @param eventDetails the request body containing the new details of the Event
     * @return a response entity with a success message and HTTP status code
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @Valid @RequestBody Event eventDetails) {
        eventsService.updateEvent(id, eventDetails);
        return new ResponseEntity<>("Event has been successfully modified", HttpStatus.OK);
    }

    /**
     * This method deletes an existing Event.
     *
     * @param id the ID of the Event to delete
     * @return a response entity with a success message and HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventsService.deleteEvent(id);
        return new ResponseEntity<>("Event has been successfully deleted", HttpStatus.NO_CONTENT);
    }

}