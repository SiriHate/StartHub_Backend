package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.forms.LoginForm;
import java.util.List;

public interface MemberService {

    public void memberRegistration(Member member);

    public void activateMemberAccount(Long id);

    public String memberLogin(LoginForm loginForm);

    public void memberPasswordRecovery(String newPassword);

    public List<Member> getAllMembers();

    public Member getMemberById(Long id);

    public Member memberUpdate(Long id, Member member);

    public void deleteMemberById(Long id);

    public Member findMemberByUsername(String username);

}
