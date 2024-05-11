package org.siri_hate.chat_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "sender")
    String sender;

    @Column(name = "recipient")
    String recipient;

    @Column(name = "message")
    String message;

}
