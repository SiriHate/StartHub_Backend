package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.MismatchedPasswordException;
import org.siri_hate.user_service.exception.UserAlreadyExistsException;
import org.siri_hate.user_service.model.dto.mapper.MemberMapper;
import org.siri_hate.user_service.model.dto.request.ChangePasswordForm;
import org.siri_hate.user_service.model.dto.request.ChangePasswordTokenRequest;
import org.siri_hate.user_service.model.dto.request.RecoveryPasswordRequest;
import org.siri_hate.user_service.model.dto.request.member.*;
import org.siri_hate.user_service.model.dto.response.member.MemberFullResponse;
import org.siri_hate.user_service.model.dto.response.member.MemberSummaryResponse;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.enums.UserRole;
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

    final private MemberMapper memberMapper;

    @Autowired
    private MemberServiceImpl(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder,
            @Lazy ConfirmationService confirmationService,
            NotificationService notificationService,
            MemberMapper memberMapper
                             ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationService = confirmationService;
        this.notificationService = notificationService;
        this.memberMapper = memberMapper;
    }

    @Override
    @Transactional
    public void memberRegistration(MemberRegistrationRequest member) {

        Member memberEntity = memberMapper.toMemberFromRegistration(member);

        if (memberRepository.findMemberByUsername(memberEntity.getUsername()) != null) {
            throw new UserAlreadyExistsException("Member with provided email or phone already exists!");
        }

        memberEntity.setPassword(passwordEncoder.encode(memberEntity.getPassword()));
        memberEntity.setRole(UserRole.MEMBER.name());
        memberRepository.save(memberEntity);
        // TODO Вернуть после дебага
        //confirmationService.sendRegistrationConfirmation(memberEntity.getId(), memberEntity.getName(), memberEntity.getEmail());
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

        Member member = memberRepository.findMemberByEmail(email);

        if (member == null) {
            throw new EntityNotFoundException("Member with email: " + email + " not found!");
        }

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

        Member member = memberRepository.findMemberByUsername(username);

        if (member == null) {
            throw new EntityNotFoundException("Member with id: " + username + " not found!");
        }

        if (passwordEncoder.matches(currentPassword, member.getPassword())) {
            member.setPassword(passwordEncoder.encode(newPassword));
        } else {
            throw new MismatchedPasswordException("Entered password does not match the current one");
        }

        memberRepository.save(member);
    }

    @Override
    public List<MemberSummaryResponse> getAllMembers() {

        List<Member> memberList = memberRepository.findAll();

        if (memberList.isEmpty()) {
            throw new EntityNotFoundException("No member was found!");
        }

        return memberMapper.toMemberSummaryResponseList(memberList);
    }

    public List<MemberSummaryResponse> getAllVisibleMembers() {

        List<Member> members = memberRepository.findMembersByProfileHiddenFlagIsFalse();

        if (members.isEmpty()) {
            throw new EntityNotFoundException("No member was found!");
        }

        return memberMapper.toMemberSummaryResponseList(members);
    }

    @Override
    public MemberFullResponse getMemberById(Long id) {

        Optional<Member> member = memberRepository.findById(id);

        if (member.isEmpty()) {
            throw new EntityNotFoundException("Member with id: " + id + " not found!");
        }

        return memberMapper.toMemberFullResponse(member.get());
    }

    @Override
    public MemberFullResponse getMemberByUsername(String username) {

        Member member = memberRepository.findMemberByUsername(username);

        if (member == null) {
            throw new EntityNotFoundException("Member with username: " + username + " not found!");
        }

        return memberMapper.toMemberFullResponse(member);
    }

    @Override
    public List<MemberSummaryResponse> searchMemberByUsername(String username) {

        List<Member> members = memberRepository.findMemberByUsernameStartingWithIgnoreCase(username);

        if (members.isEmpty()) {
            throw new EntityNotFoundException("No member found with username: " + username + " not found!");
        }

        return memberMapper.toMemberSummaryResponseList(members);
    }

    @Override
    @Transactional
    public MemberFullResponse memberUpdate(Long id, MemberFullRequest member) {

        Member currentMember = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Member with id: " + id + " not found!"));

        Member updatedMember = memberMapper.memberUpdateFullData(member, currentMember);

        memberRepository.save(updatedMember);
        return memberMapper.toMemberFullResponse(updatedMember);
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

        Member member = memberRepository.findMemberByUsername(username);

        if (member != null) {
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
    public void memberChangeAvatar(String username, MemberChangeAvatarRequest avatar) {

        Member member = memberRepository.findMemberByUsername(username);

        if (member == null) {
            throw new EntityNotFoundException("Member with username: " + username + " not found!");
        }

        Member updatedMember = memberMapper.memberUpdateAvatar(avatar, member);
        memberRepository.save(updatedMember);
    }

    @Override
    @Transactional
    public MemberFullResponse memberChangePersonalInfo(String username, MemberProfileDataRequest profileDataRequest) {

        Member member = memberRepository.findMemberByUsername(username);

        if (member == null) {
            throw new EntityNotFoundException("Member with username: " + username + " not found!");
        }

        Member updatedMember = memberMapper.memberUpdateProfileData(profileDataRequest, member);

        memberRepository.save(updatedMember);
        return memberMapper.toMemberFullResponse(updatedMember);
    }

    @Override
    public MemberFullResponse findMemberByUsername(String username) {

        Member member = memberRepository.findMemberByUsername(username);

        if (member == null) {
            throw new EntityNotFoundException("Member with username: " + username + " not found!");
        }

        return memberMapper.toMemberFullResponse(member);
    }

    @Override
    @Transactional
    public MemberFullResponse changeMemberProfileVisibility(
            MemberChangeProfileVisibilityRequest request, String username
                                                           ) {
        Member member = memberRepository.findMemberByUsername(username);

        if (member == null) {
            throw new EntityNotFoundException("Member with username: " + username + " not found!");
        }

        member.setProfileHiddenFlag(request.getProfileHiddenFlag());
        memberRepository.save(member);

        return memberMapper.toMemberFullResponse(member);
    }

}
