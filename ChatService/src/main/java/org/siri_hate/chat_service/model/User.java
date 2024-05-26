package org.siri_hate.chat_service.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @ManyToMany
    @JoinTable(
            name = "user_personal_chats",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "personal_chat_id")
    )
    private List<PersonalChat> personalChats;

    @ManyToMany(mappedBy = "users")
    private List<GroupChat> groupChats;

}
