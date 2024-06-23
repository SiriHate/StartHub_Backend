package org.siri_hate.main_service.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * This class represents the Event entity.
 * It includes the id, eventName, category, eventDescription, EventDate, onlineConferenceLink, and project of the event.
 * It is mapped to the "events" table in the database.
 */
@Entity
@Table(name = "events")
public class Event {

    /**
     * The id of the event.
     * It is the primary key in the "events" table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the event.
     */
    @Column(name = "name")
    private String eventName;

    /**
     * The category of the event.
     */
    @Column(name = "category")
    private String category;

    /**
     * The description of the event.
     */
    @Column(name = "description")
    private String eventDescription;

    /**
     * The date of the event.
     */
    @Column(name = "date")
    private LocalDateTime EventDate;

    /**
     * The link for the online conference of the event.
     */
    @Column(name = "online_conference_link")
    private String onlineConferenceLink;

    /**
     * The project associated with the event.
     * It is a foreign key referencing the "project" table.
     */
    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Project project;

    /**
     * Default constructor.
     */
    public Event() { }

    /**
     * Constructor with all fields.
     *
     * @param eventName            the name of the event
     * @param category             the category of the event
     * @param eventDescription     the description of the event
     * @param eventDate            the date of the event
     * @param onlineConferenceLink the link for the online conference of the event
     */
    public Event(
            String eventName,
            String category,
            String eventDescription,
            LocalDateTime eventDate,
            String onlineConferenceLink
                ) {
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
     * @return the name of the event
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * @param eventName the name to set
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
     * @param eventDescription the description to set
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
     * @param eventDate the date to set
     */
    public void setEventDate(LocalDateTime eventDate) {
        EventDate = eventDate;
    }

    /**
     * @return the link for the online conference of the event
     */
    public String getOnlineConferenceLink() {
        return onlineConferenceLink;
    }

    /**
     * @param onlineConferenceLink the link to set
     */
    public void setOnlineConferenceLink(String onlineConferenceLink) {
        this.onlineConferenceLink = onlineConferenceLink;
    }

    /**
     * @return the project associated with the event
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

}