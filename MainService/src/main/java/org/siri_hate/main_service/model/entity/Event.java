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
    Long id;

    @Column(name = "owner")
    String username;

    @Column(name = "name")
    String eventName;

    @Column(name = "category")
    String category;

    @Column(name = "description")
    String eventDescription;

    @Column(name = "date")
    LocalDateTime EventDate;

    @Column(name = "online_conference_link")
    String onlineConferenceLink;

    public Event() {}

    public Event(Long id, String username, String eventName, String category, String eventDescription, LocalDateTime eventDate, String onlineConferenceLink) {
        this.id = id;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(username, event.username) && Objects.equals(eventName, event.eventName) && Objects.equals(category, event.category) && Objects.equals(eventDescription, event.eventDescription) && Objects.equals(EventDate, event.EventDate) && Objects.equals(onlineConferenceLink, event.onlineConferenceLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, eventName, category, eventDescription, EventDate, onlineConferenceLink);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", eventName='" + eventName + '\'' +
                ", category='" + category + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", EventDate=" + EventDate +
                ", onlineConferenceLink='" + onlineConferenceLink + '\'' +
                '}';
    }

}
