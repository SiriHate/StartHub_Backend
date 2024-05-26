package org.siri_hate.chat_service.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "group_chats")
public class GroupChat extends Chat {

    @Column(name = "chat_name", nullable = false)
    private String chatName;

    @ManyToMany
    @JoinTable(
            name = "group_chat_user",
            joinColumns = @JoinColumn(name = "group_chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public GroupChat() {}

    public GroupChat(Long id, List<Message> messages, String chatName, List<User> users) {
        super(id, messages);
        this.chatName = chatName;
        this.users = users;
    }

    public String getChatName() {
        return chatName;
    }

    public List<User> getUsers() {
        return users;
    }

}
