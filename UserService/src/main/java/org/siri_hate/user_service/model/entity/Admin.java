package org.siri_hate.user_service.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;

/**
 * Entity for Admin.
 * This class is used to map the admins table in the database.
 * It extends the User class and provides additional fields and methods specific to an Admin.
 */
@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {

    /**
     * The ID of the admin.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the admin.
     */
    @Column(name = "name")
    private String name;

    /**
     * Default constructor.
     */
    public Admin() {}

    /**
     * Constructor with parameters.
     *
     * @param id The ID of the admin.
     * @param name The name of the admin.
     */
    public Admin(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the ID of the admin.
     *
     * @return The ID of the admin.
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the admin.
     *
     * @param id The ID of the admin.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the authorities of the admin.
     *
     * @return The authorities of the admin.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * Checks if the admin's account is not expired.
     *
     * @return false as the admin's account is always considered expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Checks if the admin's account is not locked.
     *
     * @return false as the admin's account is always considered locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * Checks if the admin's credentials are not expired.
     *
     * @return false as the admin's credentials are always considered expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

}