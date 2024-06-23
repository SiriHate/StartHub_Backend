package org.siri_hate.user_service.model.dto.response.member;

/**
 * DTO for MemberSummaryResponse.
 * This class is used to transfer data related to summary member responses.
 * It contains an ID, a username, a name, an avatar URL, and a specialization.
 */
public class MemberSummaryResponse {

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
     * The name of the member.
     * This is typically the real name of the member.
     */
    private String name;

    /**
     * The avatar URL of the member.
     * This is typically a URL to the member's avatar image.
     */
    private String avatarUrl;

    /**
     * The specialization of the member.
     * This is typically the area of expertise of the member.
     */
    private String specialization;

    /**
     * Default constructor.
     * Initializes a new instance of the MemberSummaryResponse class.
     */
    public MemberSummaryResponse() { }

    /**
     * Constructor with parameters.
     * Initializes a new instance of the MemberSummaryResponse class with an ID, a username, a name, an avatar URL, and a specialization.
     *
     * @param id The ID of the member.
     * @param username The username of the member.
     * @param name The name of the member.
     * @param avatarUrl The avatar URL of the member.
     * @param specialization The specialization of the member.
     */
    public MemberSummaryResponse(
            Long id,
            String username,
            String name,
            String avatarUrl,
            String specialization
                                ) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.specialization = specialization;
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

}