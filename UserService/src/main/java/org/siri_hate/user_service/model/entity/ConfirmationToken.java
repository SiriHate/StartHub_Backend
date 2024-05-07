package org.siri_hate.user_service.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "token_type")
    private String tokenType;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name="token_value")
    private String tokenValue;

    public ConfirmationToken(String tokenType, Long userId, String name, String email, String tokenValue) {
        this.tokenType = tokenType;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.tokenValue = tokenValue;
    }

}
