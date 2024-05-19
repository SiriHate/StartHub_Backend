package org.siri_hate.user_service.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonDeserialize
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    @NotBlank(message = "Username id should not be null")
    private String username;

    @Column(name = "password", nullable = false)
    @JsonSerialize
    @NotBlank(message = "Password id should not be null")
    @Size(min = 8, message = "Password must contain more than 8 characters")
    private String password;

    @Column(name = "role", nullable = false)
    @JsonDeserialize
    private String role;

    @Column(name = "account_enabled", nullable = false)
    @JsonDeserialize
    private boolean isEnabled;

}
