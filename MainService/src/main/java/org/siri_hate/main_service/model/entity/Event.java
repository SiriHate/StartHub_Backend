package org.siri_hate.main_service.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
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

}
