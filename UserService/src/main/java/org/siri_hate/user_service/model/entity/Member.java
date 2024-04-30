package org.siri_hate.user_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.siri_hate.user_service.model.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Member extends User {

    {
        setRole(UserRole.MEMBER.getValue());
        setEnabled(false);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "avatar")
    private Byte avatar;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name should not be null")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email should not be null")
    @Email(message = "Must provide a valid email address")
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    @NotBlank(message = "Phone should not be null")
    private String phone;

    @Column(name = "birth_day", nullable = false)
    @NotNull(message = "Birth day should not be null")
    private LocalDate birthDay;

    @Column(name = "account_enabled", nullable = false)
    @JsonIgnore
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
