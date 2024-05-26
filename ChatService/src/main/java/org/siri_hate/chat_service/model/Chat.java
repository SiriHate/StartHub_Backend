package org.siri_hate.chat_service.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "chats")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Message> messages;

    public Chat() {}

    public Chat(Long id, List<Message> messages) {
        this.id = id;
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
