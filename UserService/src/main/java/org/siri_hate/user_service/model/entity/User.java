package org.siri_hate.user_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"id", "role"})
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    @NotBlank(message = "Username id should not be null")
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password id should not be null")
    @Size(min = 8, message = "Password must contain more than 8 characters")
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

}
