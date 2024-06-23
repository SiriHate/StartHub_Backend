package org.siri_hate.user_service.model.dto.request.member;

import java.time.LocalDate;

/**
 * DTO for full member request.
 * This class is used to transfer data related to member requests.
 * It contains the username, password, role, enabled status, avatar URL, name, specialization ID, about section, email, phone, birthday, and profile visibility of the member.
 */
public class MemberFullRequest {

    /**
     * The username of the member.
     * This is typically a unique identifier for the member in the system.
     */
    private String username;

    /**
     * The password of the member.
     * This is typically a secret string that the member uses to authenticate themselves.
     */
    private String password;

    /**
     * The role of the member.
     * This is typically the role that the member has in the system.
     */
    private String role;

    /**
     * The flag indicating whether the member is enabled or not.
     * If true, the member is enabled. If false, the member is disabled.
     */
    private boolean isEnabled;

    /**
     * The avatar URL of the member.
     * This is typically the URL of the avatar image that the member uses.
     */
    private String avatarUrl;

    /**
     * The name of the member.
     * This is typically the full name of the member.
     */
    private String name;

    /**
     * The ID of the member's specialization.
     * This is typically a unique identifier for the member's specialization in the system.
     */
    private Long specializationId;

    /**
     * The about section of the member.
     * This is typically a brief description of the member.
     */
    private String about;

    /**
     * The email of the member.
     * This is typically the email address that the member uses for communication.
     */
    private String email;

    /**
     * The phone number of the member.
     * This is typically the phone number that the member uses for communication.
     */
    private String phone;

    /**
     * The birthday of the member.
     * This is typically the date of birth of the member.
     */
    private LocalDate birthday;

    /**
     * The flag indicating whether the member's profile is hidden or not.
     * If true, the profile is hidden. If false, the profile is visible.
     */
    private boolean profileHiddenFlag;

    /**
     * Default constructor for the MemberFullRequest class.
     */
    public MemberFullRequest() { }

    /**
     * Constructor for the MemberFullRequest class.
     *
     * @param username The username of the member.
     * @param password The password of the member.
     * @param role The role of the member.
     * @param isEnabled The flag indicating whether the member is enabled or not.
     * @param avatarUrl The avatar URL of the member.
     * @param name The name of the member.
     * @param specializationId The ID of the member's specialization.
     * @param about The about section of the member.
     * @param email The email of the member.
     * @param phone The phone number of the member.
     * @param birthday The birthday of the member.
     * @param profileHiddenFlag The flag indicating whether the member's profile is hidden or not.
     */
    public MemberFullRequest(
            String username,
            String password,
            String role,
            boolean isEnabled,
            String avatarUrl,
            String name,
            Long specializationId,
            String about,
            String email,
            String phone,
            LocalDate birthday,
            boolean profileHiddenFlag
                            ) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isEnabled = isEnabled;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.specializationId = specializationId;
        this.about = about;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.profileHiddenFlag = profileHiddenFlag;
    }

    /**
     * Gets the username of the member.
     *
     * @return The username of the member.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the member.
     *
     * @param username The username of the member.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the member.
     *
     * @return The password of the member.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the member.
     *
     * @param password The password of the member.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the member.
     *
     * @return The role of the member.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the member.
     *
     * @param role The role of the member.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the enabled status of the member.
     *
     * @return The enabled status of the member.
     */
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Sets the enabled status of the member.
     *
     * @param enabled The enabled status of the member.
     */
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    /**
     * Gets the avatar URL of the member.
     *
     * @return The avatar URL of the member.
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Sets the avatar URL of the member.
     *
     * @param avatarUrl The avatar URL of the member.
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
     * Gets the ID of the member's specialization.
     *
     * @return The ID of the member's specialization.
     */
    public Long getSpecializationId() {
        return specializationId;
    }

    /**
     * Sets the ID of the member's specialization.
     *
     * @param specializationId The ID of the member's specialization.
     */
    public void setSpecialization(Long specializationId) {
        this.specializationId = specializationId;
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
     * Gets the flag indicating whether the member's profile is hidden or not.
     *
     * @return The flag indicating whether the member's profile is hidden or not.
     */
    public boolean isProfileHiddenFlag() {
        return profileHiddenFlag;
    }

    /**
     * Sets the flag indicating whether the member's profile is hidden or not.
     *
     * @param profileHiddenFlag The flag indicating whether the member's profile is hidden or not.
     */
    public void setProfileHiddenFlag(boolean profileHiddenFlag) {
        this.profileHiddenFlag = profileHiddenFlag;
    }

}