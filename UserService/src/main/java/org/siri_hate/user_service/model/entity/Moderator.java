package org.siri_hate.user_service.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;

/**
 * Entity for Moderator.
 * This class is used to map the moderators table in the database.
 * It extends the User class and provides additional fields and methods specific to a Moderator.
 */
@Entity
@Table(name = "moderators")
@PrimaryKeyJoinColumn(name = "user_id")
public class Moderator extends User {

    /**
     * The ID of the moderator.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the moderator.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The employee ID of the moderator.
     */
    @Column(name = "employee_id", nullable = false, unique = true)
    private Long employeeId;

    /**
     * Default constructor.
     */
    public Moderator() {}

    /**
     * Constructor with parameters.
     *
     * @param id The ID of the moderator.
     * @param username The username of the moderator.
     * @param password The password of the moderator.
     * @param role The role of the moderator.
     * @param isEnabled The status of the moderator's account.
     * @param name The name of the moderator.
     * @param employeeId The employee ID of the moderator.
     */
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

    /**
     * Gets the ID of the moderator.
     *
     * @return The ID of the moderator.
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the moderator.
     *
     * @param id The ID of the moderator.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the moderator.
     *
     * @return The name of the moderator.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the moderator.
     *
     * @param name The name of the moderator.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the employee ID of the moderator.
     *
     * @return The employee ID of the moderator.
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the employee ID of the moderator.
     *
     * @param employeeId The employee ID of the moderator.
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets the authorities of the moderator.
     *
     * @return The authorities of the moderator.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * Checks if the moderator's account is not expired.
     *
     * @return false as the moderator's account is always considered expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Checks if the moderator's account is not locked.
     *
     * @return false as the moderator's account is always considered locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * Checks if the moderator's credentials are not expired.
     *
     * @return false as the moderator's credentials are always considered expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

}