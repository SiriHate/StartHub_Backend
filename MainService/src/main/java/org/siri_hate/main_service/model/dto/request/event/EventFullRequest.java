package org.siri_hate.main_service.model.dto.request.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * This class represents a full request for an Event.
 * It includes fields for the event name, category, description, date, and online conference link.
 * All fields are mandatory.
 */
public class EventFullRequest {

    @NotBlank(message = "Event name should not be blank")
    String eventName;

    @NotBlank(message = "Category should not be blank")
    String category;

    @NotBlank(message = "Event description should not be blank")
    String eventDescription;

    @NotNull(message = "Event date should not be blank")
    LocalDateTime EventDate;

    @NotBlank(message = "Event location should not be blank")
    String onlineConferenceLink;

    /**
     * Default constructor for EventFullRequest.
     */
    public EventFullRequest() { }

    /**
     * Constructor for EventFullRequest with all fields.
     *
     * @param eventName            the name of the event
     * @param category             the category of the event
     * @param eventDescription     the description of the event
     * @param EventDate            the date of the event
     * @param onlineConferenceLink the online conference link of the event
     */
    public EventFullRequest(
            String eventName,
            String category,
            String eventDescription,
            LocalDateTime EventDate,
            String onlineConferenceLink
                           ) {
        this.eventName = eventName;
        this.category = category;
        this.eventDescription = eventDescription;
        this.EventDate = EventDate;
        this.onlineConferenceLink = onlineConferenceLink;
    }

    /**
     * Returns the name of the event.
     *
     * @return the name of the event
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the name of the event.
     *
     * @param eventName the name of the event
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Returns the category of the event.
     *
     * @return the category of the event
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the event.
     *
     * @param category the category of the event
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the description of the event.
     *
     * @return the description of the event
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * Sets the description of the event.
     *
     * @param eventDescription the description of the event
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    /**
     * Returns the date of the event.
     *
     * @return the date of the event
     */
    public LocalDateTime getEventDate() {
        return EventDate;
    }

    /**
     * Sets the date of the event.
     *
     * @param eventDate the date of the event
     */
    public void setEventDate(LocalDateTime eventDate) {
        EventDate = eventDate;
    }

    /**
     * Returns the online conference link of the event.
     *
     * @return the online conference link of the event
     */
    public String getOnlineConferenceLink() {
        return onlineConferenceLink;
    }

    /**
     * Sets the online conference link of the event.
     *
     * @param onlineConferenceLink the online conference link of the event
     */
    public void setOnlineConferenceLink(String onlineConferenceLink) {
        this.onlineConferenceLink = onlineConferenceLink;
    }

}