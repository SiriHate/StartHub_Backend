package org.siri_hate.user_service.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Entity for Member.
 * This class is used to map the members table in the database.
 * It extends the User class and provides additional fields and methods specific to a Member.
 */
@Entity
@Table(name = "members")
@PrimaryKeyJoinColumn(name = "user_id")
public class Member extends User {

    /**
     * The ID of the member.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The URL of the member's avatar.
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * The name of the member.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The specialization of the member.
     */
    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private SpecialistSpecialization specialization;

    /**
     * The about section of the member.
     */
    @Column(name = "about")
    private String about;

    /**
     * The email of the member.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * The phone number of the member.
     */
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    /**
     * The birthday of the member.
     */
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    /**
     * The flag indicating if the member's profile is hidden.
     */
    @Column(name = "is_hidden", nullable = false)
    private Boolean profileHiddenFlag;

    /**
     * Default constructor.
     */
    public Member() {}

    /**
     * Constructor with parameters.
     *
     * @param id The ID of the member.
     * @param username The username of the member.
     * @param password The password of the member.
     * @param role The role of the member.
     * @param isEnabled The status of the member's account.
     * @param avatarUrl The URL of the member's avatar.
     * @param name The name of the member.
     * @param specialization The specialization of the member.
     * @param about The about section of the member.
     * @param email The email of the member.
     * @param phone The phone number of the member.
     * @param birthday The birthday of the member.
     * @param profileHiddenFlag The flag indicating if the member's profile is hidden.
     */
    public Member(
            Long id,
            String username,
            String password, String role,
            Boolean isEnabled,
            String avatarUrl,
            String name,
            SpecialistSpecialization specialization,
            String about,
            String email,
            String phone,
            LocalDate birthday,
            Boolean profileHiddenFlag
                 ) {
        super(id, username, password, role, isEnabled);
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.specialization = specialization;
        this.about = about;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.profileHiddenFlag = profileHiddenFlag;
    }

    /**
     * Gets the ID of the member.
     *
     * @return The ID of the member.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the member.
     *
     * @param id The ID of the member.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the URL of the member's avatar.
     *
     * @return The URL of the member's avatar.
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Sets the URL of the member's avatar.
     *
     * @param avatarUrl The URL of the member's avatar.
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * Gets the name of the member.
     *
     * @return The name of the member.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the member.
     *
     * @param name The name of the member.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the specialization of the member.
     *
     * @return The specialization of the member.
     */
    public SpecialistSpecialization getSpecialization() {
        return specialization;
    }

    /**
     * Sets the specialization of the member.
     *
     * @param specialization The specialization of the member.
     */
    public void setSpecialization(SpecialistSpecialization specialization) {
        this.specialization = specialization;
    }

    /**
     * Gets the about section of the member.
     *
     * @return The about section of the member.
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the about section of the member.
     *
     * @param about The about section of the member.
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * Gets the email of the member.
     *
     * @return The email of the member.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the member.
     *
     * @param email The email of the member.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the member.
     *
     * @return The phone number of the member.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the member.
     *
     * @param phone The phone number of the member.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the birthday of the member.
     *
     * @return The birthday of the member.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Sets the birthday of the member.
     *
     * @param birthday The birthday of the member.
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets the flag indicating if the member's profile is hidden.
     *
     * @return The flag indicating if the member's profile is hidden.
     */
    public Boolean getProfileHiddenFlag() {
        return profileHiddenFlag;
    }

    /**
     * Sets the flag indicating if the member's profile is hidden.
     *
     * @param profileHiddenFlag The flag indicating if the member's profile is hidden.
     */
    public void setProfileHiddenFlag(Boolean profileHiddenFlag) {
        this.profileHiddenFlag = profileHiddenFlag;
    }

    /**
     * Gets the authorities of the member.
     *
     * @return The authorities of the member.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * Checks if the member's account is not expired.
     *
     * @return false as the member's account is always considered expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Checks if the member's account is not locked.
     *
     * @return false as the member's account is always considered locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * Checks if the member's credentials are not expired.
     *
     * @return false as the member's credentials are always considered expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

}