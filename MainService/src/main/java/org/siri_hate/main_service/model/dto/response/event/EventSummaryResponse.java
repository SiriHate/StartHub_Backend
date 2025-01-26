package org.siri_hate.main_service.model.dto.response.event;

import java.time.LocalDateTime;


public class EventSummaryResponse {

    private Long id;
    private String eventName;
    private String category;
    private LocalDateTime EventDate;

    
    public EventSummaryResponse() { }

    
    public EventSummaryResponse(Long id, String eventName, String category, LocalDateTime eventDate) {
        this.id = id;
        this.eventName = eventName;
        this.category = category;
        EventDate = eventDate;
    }

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getEventName() {
        return eventName;
    }

    
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    
    public String getCategory() {
        return category;
    }

    
    public void setCategory(String category) {
        this.category = category;
    }

    
    public LocalDateTime getEventDate() {
        return EventDate;
    }

    
    public void setEventDate(LocalDateTime eventDate) {
        EventDate = eventDate;
    }

}