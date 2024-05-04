package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.request.ChangePasswordForm;
import org.siri_hate.user_service.model.request.PersonalData;
import org.siri_hate.user_service.model.request.LoginForm;

import java.util.List;

public interface MemberService {

    void memberRegistration(Member member);

    void activateMemberAccount(Long id);

    String memberLogin(LoginForm loginForm);

    void memberPasswordRecoveryRequest(String newPassword);

    void memberPasswordRecoveryConfirmation(String newPassword);

    void memberPasswordChange(String username, ChangePasswordForm changePasswordForm);

    List<Member> getAllMembers();

    Member getMemberById(Long id);

    Member memberUpdate(Long id, Member member);

    void deleteMemberById(Long id);

    void deleteMemberByUserName(String username);

    void memberChangeAvatar(String username, Byte avatar);

    void memberChangePersonalInfo(String username, PersonalData personalData);

    Member findMemberByUsername(String username);

}
