package org.siri_hate.user_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name="confirmation_token")
    private String confirmationToken;

    public ConfirmationToken(Long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.confirmationToken = generateTokenBasedOnUserId(userId);
    }

    private String generateTokenBasedOnUserId(Long userId) {

        String source = userId + "-" + System.currentTimeMillis();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(source.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm could not be found", e);
        }

    }

}
