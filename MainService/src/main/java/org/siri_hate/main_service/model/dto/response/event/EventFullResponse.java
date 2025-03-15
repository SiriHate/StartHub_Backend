package org.siri_hate.main_service.model.dto.response.event;

import java.time.LocalDateTime;

public class EventFullResponse {

  private Long id;
  private String username;
  private String eventName;
  private String category;
  private String eventDescription;
  private LocalDateTime EventDate;
  private String onlineConferenceLink;

  public EventFullResponse() {}

  public EventFullResponse(
      Long id,
      String username,
      String eventName,
      String category,
      String eventDescription,
      LocalDateTime eventDate,
      String onlineConferenceLink) {
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
}
