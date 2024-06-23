package org.siri_hate.user_service.model.dto.response.member;

import java.time.LocalDate;

/**
 * DTO for full member response.
 * This class is used to transfer data related to full member responses.
 * It contains an ID, a username, an avatar URL, a name, a specialization, about info, email, phone, birthday, and a profile hidden flag.
 */
public class MemberFullResponse {

    /**
     * The ID of the member.
     * This is typically a unique identifier for the member.
     */
    private Long id;

    /**
     * The username of the member.
     * This is typically a unique identifier for the member in the system.
     */
    private String username;

    /**
     * The avatar URL of the member.
     * This is typically a URL to the member's avatar image.
     */
    private String avatarUrl;

    /**
     * The name of the member.
     * This is typically the real name of the member.
     */
    private String name;

    /**
     * The specialization of the member.
     * This is typically the area of expertise of the member.
     */
    private String specialization;

    /**
     * The information about the member.
     * This is typically a brief description or bio of the member.
     */
    private String about;

    /**
     * The email of the member.
     * This is typically the email address of the member.
     */
    private String email;

    /**
     * The phone number of the member.
     * This is typically the contact number of the member.
     */
    private String phone;

    /**
     * The birthday of the member.
     * This is typically the date of birth of the member.
     */
    private LocalDate birthday;

    /**
     * The flag indicating if the member's profile is hidden.
     * This is typically a boolean value indicating the visibility of the member's profile.
     */
    private boolean profileHiddenFlag;

    /**
     * Default constructor.
     * Initializes a new instance of the MemberFullResponse class.
     */
    public MemberFullResponse() { }

    /**
     * Constructor with parameters.
     * Initializes a new instance of the MemberFullResponse class with an ID, a username, an avatar URL, a name, a specialization, about info, email, phone, birthday, and a profile hidden flag.
     *
     * @param id The ID of the member.
     * @param username The username of the member.
     * @param avatarUrl The avatar URL of the member.
     * @param name The name of the member.
     * @param specialization The specialization of the member.
     * @param about The information about the member.
     * @param email The email of the member.
     * @param phone The phone number of the member.
     * @param birthday The birthday of the member.
     * @param profileHiddenFlag The flag indicating if the member's profile is hidden.
     */
    public MemberFullResponse(
            Long id,
            String username,
            String avatarUrl,
            String name,
            String specialization,
            String about,
            String email,
            String phone,
            LocalDate birthday,
            boolean profileHiddenFlag
                             ) {
        this.id = id;
        this.username = username;
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
     * Gets the specialization of the member.
     *
     * @return The specialization of the member.
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the specialization of the member.
     *
     * @param specialization The specialization of the member.
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Gets the information about the member.
     *
     * @return The information about the member.
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the information about the member.
     *
     * @param about The information about the member.
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
    public boolean isProfileHiddenFlag() {
        return profileHiddenFlag;
    }

    /**
     * Sets the flag indicating if the member's profile is hidden.
     *
     * @param profileHiddenFlag The flag indicating if the member's profile is hidden.
     */
    public void setProfileHiddenFlag(boolean profileHiddenFlag) {
        this.profileHiddenFlag = profileHiddenFlag;
    }

}