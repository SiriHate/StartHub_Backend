package org.siri_hate.user_service.model.dto.request.member;

/**
 * DTO for member profile visibility change request.
 * This class is used to transfer data related to member profile visibility change requests.
 * It contains a flag indicating whether the profile is hidden or not.
 */
public class MemberChangeProfileVisibilityRequest {

    /**
     * The flag indicating whether the profile is hidden or not.
     * If true, the profile is hidden. If false, the profile is visible.
     */
    private Boolean profileHiddenFlag;

    /**
     * Default constructor for the MemberChangeProfileVisibilityRequest class.
     */
    public MemberChangeProfileVisibilityRequest() { }

    /**
     * Constructor for the MemberChangeProfileVisibilityRequest class.
     *
     * @param profileHiddenFlag The flag indicating whether the profile is hidden or not.
     */
    public MemberChangeProfileVisibilityRequest(Boolean profileHiddenFlag) {
        this.profileHiddenFlag = profileHiddenFlag;
    }

    /**
     * Gets the flag indicating whether the profile is hidden or not.
     *
     * @return The flag indicating whether the profile is hidden or not.
     */
    public boolean getProfileHiddenFlag() {
        return profileHiddenFlag;
    }

    /**
     * Sets the flag indicating whether the profile is hidden or not.
     *
     * @param profileHiddenFlag The flag indicating whether the profile is hidden or not.
     */
    public void setProfileHidden(Boolean profileHiddenFlag) {
        this.profileHiddenFlag = profileHiddenFlag;
    }

}