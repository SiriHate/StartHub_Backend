package org.siri_hate.main_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "event_owner")
    String username;

    @Column(name = "event_name")
    String eventName;

    @Column(name = "event_description")
    String eventDescription;

    @Column(name = "event_date")
    LocalDateTime EventDate;

    @Column(name = "online_conference_link")
    String onlineConferenceLink;

}
