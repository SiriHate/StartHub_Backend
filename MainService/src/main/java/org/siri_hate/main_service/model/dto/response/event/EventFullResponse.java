package org.siri_hate.main_service.model.dto.response.event;

import java.time.LocalDateTime;

/**
 * This class represents the full response for an event.
 * It includes all the details of an event that can be sent as a response.
 */
public class EventFullResponse {

    private Long id;
    private String username;
    private String eventName;
    private String category;
    private String eventDescription;
    private LocalDateTime EventDate;
    private String onlineConferenceLink;

    /**
     * Default constructor.
     */
    public EventFullResponse() { }

    /**
     * Constructor with all fields.
     *
     * @param id                   the id of the event
     * @param username             the username of the event creator
     * @param eventName            the name of the event
     * @param category             the category of the event
     * @param eventDescription     the description of the event
     * @param eventDate            the date of the event
     * @param onlineConferenceLink the online conference link of the event
     */
    public EventFullResponse(
            Long id,
            String username,
            String eventName,
            String category,
            String eventDescription,
            LocalDateTime eventDate,
            String onlineConferenceLink
                            ) {
        this.id = id;
        this.username = username;
        this.eventName = eventName;
        this.category = category;
        this.eventDescription = eventDescription;
        EventDate = eventDate;
        this.onlineConferenceLink = onlineConferenceLink;
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
     * @return the username of the event creator
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @return the description of the event
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * @param eventDescription the event description to set
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
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

    /**
     * @return the online conference link of the event
     */
    public String getOnlineConferenceLink() {
        return onlineConferenceLink;
    }

    /**
     * @param onlineConferenceLink the online conference link to set
     */
    public void setOnlineConferenceLink(String onlineConferenceLink) {
        this.onlineConferenceLink = onlineConferenceLink;
    }

}