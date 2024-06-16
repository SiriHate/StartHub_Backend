package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.dto.request.ChangePasswordForm;
import org.siri_hate.user_service.model.dto.request.ChangePasswordTokenRequest;
import org.siri_hate.user_service.model.dto.request.RecoveryPasswordRequest;
import org.siri_hate.user_service.model.dto.request.member.*;
import org.siri_hate.user_service.model.dto.response.member.MemberFullResponse;
import org.siri_hate.user_service.model.dto.response.member.MemberSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MemberService {

    void memberRegistration(MemberRegistrationRequest member);

    void activateMemberAccount(Long id);

    void memberPasswordRecoveryRequest(RecoveryPasswordRequest recoveryPasswordRequest);

    void memberPasswordRecoveryConfirmation(ChangePasswordTokenRequest changePasswordTokenRequest);

    void memberPasswordChange(String username, ChangePasswordForm changePasswordForm);

    Page<MemberSummaryResponse> getAllMembers(Pageable pageable);

    Page<MemberSummaryResponse> getAllVisibleMembers(Pageable pageable);

    Page<MemberSummaryResponse> getMembersByUsernameAndSpecialization(
            String username,
            String specialization,
            Pageable pageable
                                                                     );

    MemberFullResponse getMemberById(Long id);

    MemberFullResponse getMemberByUsername(String username);

    MemberFullResponse memberUpdate(Long id, MemberFullRequest member);

    void deleteMemberById(Long id);

    void deleteMemberByUsername(String username);

    void memberChangeAvatar(String username, MemberChangeAvatarRequest avatar);

    MemberFullResponse memberChangePersonalInfo(String username, MemberProfileDataRequest profileDataRequest);

    MemberFullResponse changeMemberProfileVisibility(MemberChangeProfileVisibilityRequest request, String username);

}
