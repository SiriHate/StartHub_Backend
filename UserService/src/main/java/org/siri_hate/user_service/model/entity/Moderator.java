package org.siri_hate.user_service.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.siri_hate.user_service.model.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "moderators")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "user_id")
public class Moderator extends User {

    {
        setRole(UserRole.MODERATOR.getValue());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonDeserialize
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name should not be null")
    String name;

    @Column(name = "employee_id", nullable = false, unique = true)
    @NotNull(message = "Employee id should not be null")
    Long employeeId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
