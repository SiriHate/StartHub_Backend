package org.siri_hate.main_service.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String eventName;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String eventDescription;

    @Column(name = "date")
    private LocalDateTime EventDate;

    @Column(name = "online_conference_link")
    private String onlineConferenceLink;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Project project;

    public Event() { }

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

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDateTime getEventDate() {
        return EventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        EventDate = eventDate;
    }

    public String getOnlineConferenceLink() {
        return onlineConferenceLink;
    }

    public void setOnlineConferenceLink(String onlineConferenceLink) {
        this.onlineConferenceLink = onlineConferenceLink;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
