package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.dto.request.*;
import org.siri_hate.user_service.model.dto.request.member.*;
import org.siri_hate.user_service.model.dto.response.member.MemberFullResponse;
import org.siri_hate.user_service.model.dto.response.member.MemberSummaryResponse;
import org.siri_hate.user_service.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {

    void memberRegistration(MemberRegistrationRequest member);

    void activateMemberAccount(Long id);

    void memberPasswordRecoveryRequest(RecoveryPasswordRequest recoveryPasswordRequest);

    void memberPasswordRecoveryConfirmation(ChangePasswordTokenRequest changePasswordTokenRequest);

    void memberPasswordChange(String username, ChangePasswordForm changePasswordForm);

    Page<MemberSummaryResponse> getAllMembers(Pageable pageable);

    Page<MemberSummaryResponse> getAllVisibleMembers(Pageable pageable);

    MemberFullResponse getMemberById(Long id);

    MemberFullResponse getMemberByUsername(String username);

    Page<MemberSummaryResponse> searchMemberByUsername(String username, Pageable pageable);

    MemberFullResponse memberUpdate(Long id, MemberFullRequest member);

    void deleteMemberById(Long id);

    void deleteMemberByUserName(String username);

    void memberChangeAvatar(String username, MemberChangeAvatarRequest avatar);

    MemberFullResponse memberChangePersonalInfo(String username, MemberProfileDataRequest profileDataRequest);

    MemberFullResponse findMemberByUsername(String username);

    MemberFullResponse changeMemberProfileVisibility(MemberChangeProfileVisibilityRequest request, String username);

}
