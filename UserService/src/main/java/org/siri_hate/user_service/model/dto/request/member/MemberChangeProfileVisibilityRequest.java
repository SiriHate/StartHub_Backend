package org.siri_hate.user_service.model.dto.request.member;

public class MemberChangeProfileVisibilityRequest {

    private boolean profileHiddenFlag;

    public MemberChangeProfileVisibilityRequest() { }

    public MemberChangeProfileVisibilityRequest(boolean profileHiddenFlag) {
        this.profileHiddenFlag = profileHiddenFlag;
    }

    public boolean getProfileHiddenFlag() {
        return profileHiddenFlag;
    }

    public void setProfileHidden(boolean profileHiddenFlag) {
        this.profileHiddenFlag = profileHiddenFlag;
    }

}
