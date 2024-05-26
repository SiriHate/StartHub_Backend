package org.siri_hate.user_service.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "moderators")
@PrimaryKeyJoinColumn(name = "user_id")
public class Moderator extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "employee_id", nullable = false, unique = true)
    Long employeeId;

    public Moderator() {}

    public Moderator(
            Long id,
            String username,
            String password,
            String role,
            boolean isEnabled,
            String name, Long employeeId) {
        super(id, username, password, role, isEnabled);
        this.name = name;
        this.employeeId = employeeId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Moderator moderator = (Moderator) o;
        return Objects.equals(id, moderator.id) && Objects.equals(name, moderator.name) && Objects.equals(employeeId, moderator.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, employeeId);
    }

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

}
