package org.siri_hate.main_service.model.dto.response.event;

import java.time.LocalDateTime;

/**
 * This class represents a summary response for an event.
 * It includes the essential details of an event that can be sent as a response.
 */
public class EventSummaryResponse {

    private Long id;
    private String eventName;
    private String category;
    private LocalDateTime EventDate;

    /**
     * Default constructor.
     */
    public EventSummaryResponse() { }

    /**
     * Constructor with all fields.
     *
     * @param id        the id of the event
     * @param eventName the name of the event
     * @param category  the category of the event
     * @param eventDate the date of the event
     */
    public EventSummaryResponse(Long id, String eventName, String category, LocalDateTime eventDate) {
        this.id = id;
        this.eventName = eventName;
        this.category = category;
        EventDate = eventDate;
    }

    /**
     * @return the id of the event
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name of the event
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * @param eventName the event name to set
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * @return the category of the event
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the date of the event
     */
    public LocalDateTime getEventDate() {
        return EventDate;
    }

    /**
     * @param eventDate the event date to set
     */
    public void setEventDate(LocalDateTime eventDate) {
        EventDate = eventDate;
    }

}