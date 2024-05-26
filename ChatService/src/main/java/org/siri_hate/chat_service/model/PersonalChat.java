package org.siri_hate.chat_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "private_chats")
public class PersonalChat extends Chat {

    @ManyToMany
    @JoinTable(
            name = "personal_chat_users",
            joinColumns = @JoinColumn(name = "personal_chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Size(min = 2, max = 2, message = "Personal chat must have exactly two users.")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        if (users.size() != 2) {
            throw new IllegalArgumentException("Personal chat must have exactly two users.");
        }
        this.users = users;
    }

}
