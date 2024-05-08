package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.MismatchedPasswordException;
import org.siri_hate.user_service.exception.UserAlreadyExistsException;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.request.*;
import org.siri_hate.user_service.repository.MemberRepository;
import org.siri_hate.user_service.service.ConfirmationService;
import org.siri_hate.user_service.service.MemberService;
import org.siri_hate.user_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;

    final private PasswordEncoder passwordEncoder;

    final private ConfirmationService confirmationService;

    final private NotificationService notificationService;

    @Autowired
    private MemberServiceImpl(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder,
            @Lazy ConfirmationService confirmationService,
            NotificationService notificationService
    ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationService = confirmationService;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public void memberRegistration(Member member) {

        if (memberRepository.findMemberByUsername(member.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Member with provided email or phone already exists!");
        }

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        //confirmationService.sendRegistrationConfirmation(member.getId(), member.getName(), member.getEmail());
    }

    @Override
    public void activateMemberAccount(Long id) {

        Optional<Member> memberOptional = memberRepository.findById(id);

        if (memberOptional.isEmpty()) {
            throw new EntityNotFoundException("Member with id: " + id + " not found!");
        }

        Member member = memberOptional.get();
        member.setEnabled(true);
        memberRepository.save(member);

        String memberName = member.getName();
        String memberEmail = member.getEmail();
        //notificationService.sendSuccessfulRegistrationNotification(memberName, memberEmail);
    }

    @Override
    public void memberPasswordRecoveryRequest(RecoveryPasswordRequest recoveryPasswordRequest) {

        String email = recoveryPasswordRequest.getEmail();

        Optional<Member> memberOptional = memberRepository.findMemberByEmail(email);

        if (memberOptional.isEmpty()) {
            throw new EntityNotFoundException("Member with email: " + email + " not found!");
        }

        Member member = memberOptional.get();

        Long memberId = member.getId();
        String memberName = member.getName();
        String memberEmail = member.getEmail();
        confirmationService.sendChangePasswordConfirmation(memberId, memberName, memberEmail);
    }

    @Override
    public void memberPasswordRecoveryConfirmation(ChangePasswordTokenRequest changePasswordTokenRequest) {

        String token = changePasswordTokenRequest.getToken();
        String newPassword = changePasswordTokenRequest.getNewPassword();

        Long userId = confirmationService.getUserIdByToken(token);
        Optional<Member> memberOptional = memberRepository.findById(userId);

        if (memberOptional.isEmpty()) {
            throw new EntityNotFoundException("Member with id: " + userId + " not found!");
        }

        Member member = memberOptional.get();

        member.setPassword(passwordEncoder.encode(newPassword));
        memberRepository.save(member);
        confirmationService.deleteConfirmationTokenByTokenValue(token);

        String memberName = member.getName();
        String memberEmail = member.getEmail();

        //notificationService.sendChangedPasswordNotification(memberName, memberEmail);
    }

    @Override
    @Transactional
    public void memberPasswordChange(String username, ChangePasswordForm changePasswordForm) {

        String currentPassword = changePasswordForm.getCurrentPassword();
        String newPassword = changePasswordForm.getNewPassword();

        Optional<Member> memberOptional = memberRepository.findMemberByUsername(username);

        if (memberOptional.isEmpty()) {
            throw new EntityNotFoundException("Member with id: " + username + " not found!");
        }

        Member member = memberOptional.get();

        if (passwordEncoder.matches(currentPassword, member.getPassword())) {
            member.setPassword(passwordEncoder.encode(newPassword));
        } else {
            throw new MismatchedPasswordException("Entered password does not match the current one");
        }

        memberRepository.save(member);
    }

    @Override
    @Transactional
    public List<Member> getAllMembers() {

        List<Member> memberList = memberRepository.findAll();

        if (memberList.isEmpty()) {
            throw new EntityNotFoundException("No member was found!");
        }

        return memberList;
    }

    @Override
    @Transactional
    public Member getMemberById(Long id) {

        Optional<Member> memberOptional = memberRepository.findById(id);

        if (memberOptional.isEmpty()) {
            throw new EntityNotFoundException("Member with id: " + id + " not found!");
        }

        return memberOptional.get();
    }

    @Override
    public Member getMemberByUsername(String username) {

        Optional<Member> memberOptional = memberRepository.findMemberByUsername(username);

        if (memberOptional.isEmpty()) {
            throw new EntityNotFoundException("Member with username: " + username + " not found!");
        }

        return memberOptional.get();
    }

    @Override
    @Transactional
    public Member memberUpdate(Long id, Member member) {

        Optional<Member> memberOptional = memberRepository.findById(id);

        if (memberOptional.isEmpty()) {
            throw new EntityNotFoundException("Member with id: " + id + " not found!");
        }

        member.setId(id);

        return memberRepository.save(member);
    }

    @Override
    @Transactional
    public void deleteMemberById(Long id) {

        Optional<Member> memberOptional = memberRepository.findById(id);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            memberRepository.delete(member);
            String name = member.getName();
            String email = member.getEmail();
            notificationService.sendDeletedAccountNotification(name, email);
        } else {
            throw new EntityNotFoundException("Member with id: " + id + " not found!");
        }

        memberRepository.delete(memberOptional.get());
    }

    @Override
    @Transactional
    public void deleteMemberByUserName(String username) {

        Optional<Member> memberOptional = memberRepository.findMemberByUsername(username);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            memberRepository.delete(member);
            String name = member.getName();
            String email = member.getEmail();
            //notificationService.sendDeletedAccountNotification(name, email);
        } else {
            throw new EntityNotFoundException("Member with username: " + username + " not found!");
        }

    }

    @Override
    @Transactional
    public void memberChangeAvatar(String username, byte[] avatar) {
        Member member = findMemberByUsername(username);
        member.setAvatar(avatar);
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void memberChangePersonalInfo(String username, PersonalData personalData) {
        Member member = findMemberByUsername(username);
        System.out.println("member = " + member);
        System.out.println("personalData" + personalData);
        member.setName(personalData.getName());
        member.setPhone(personalData.getPhone());
        member.setEmail(personalData.getEmail());
        member.setBirthday(personalData.getBirthday());
        memberRepository.save(member);
    }

    @Override
    public Member findMemberByUsername(String username) {

        Optional<Member> userOptional = memberRepository.findMemberByUsername(username);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("Member with username: " + username + " not found!");
        }

        return userOptional.get();
    }

}
