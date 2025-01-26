package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.MismatchedPasswordException;
import org.siri_hate.user_service.exception.NoSuchUserException;
import org.siri_hate.user_service.exception.UserAlreadyExistsException;
import org.siri_hate.user_service.model.dto.mapper.MemberMapper;
import org.siri_hate.user_service.model.dto.request.auth.ChangePasswordForm;
import org.siri_hate.user_service.model.dto.request.tokens.ChangePasswordTokenRequest;
import org.siri_hate.user_service.model.dto.request.auth.RecoveryPasswordRequest;
import org.siri_hate.user_service.model.dto.request.member.*;
import org.siri_hate.user_service.model.dto.response.member.MemberFullResponse;
import org.siri_hate.user_service.model.dto.response.member.MemberSummaryResponse;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.entity.SpecialistSpecialization;
import org.siri_hate.user_service.model.enums.UserRole;
import org.siri_hate.user_service.repository.MemberRepository;
import org.siri_hate.user_service.repository.adapters.MemberSpecification;
import org.siri_hate.user_service.service.ConfirmationService;
import org.siri_hate.user_service.service.MemberService;
import org.siri_hate.user_service.service.NotificationService;
import org.siri_hate.user_service.service.SpecialistSpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MemberServiceImpl implements MemberService {

    // Member repository instance
    final private MemberRepository memberRepository;

    // Password encoder instance
    final private PasswordEncoder passwordEncoder;

    // Confirmation service instance
    final private ConfirmationService confirmationService;

    // Notification service instance
    final private NotificationService notificationService;

    // Member mapper instance
    final private MemberMapper memberMapper;

    // Specialist specialization service instance
    final private SpecialistSpecializationService specialistSpecializationService;


    @Autowired
    private MemberServiceImpl(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder,
            @Lazy ConfirmationService confirmationService,
            NotificationService notificationService,
            MemberMapper memberMapper,
            SpecialistSpecializationService specialistSpecializationService
                             ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationService = confirmationService;
        this.notificationService = notificationService;
        this.memberMapper = memberMapper;
        this.specialistSpecializationService = specialistSpecializationService;
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

        confirmationService.sendRegistrationConfirmation(memberEntity);
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

        notificationService.sendSuccessfulRegistrationNotification(memberName, memberEmail);
    }


    @Override
    public void memberPasswordRecoveryRequest(RecoveryPasswordRequest recoveryPasswordRequest) {

        String email = recoveryPasswordRequest.getEmail();

        Member member = memberRepository.findMemberByEmail(email);

        if (member == null) {
            throw new EntityNotFoundException("Member with email: " + email + " not found!");
        }

        confirmationService.sendChangePasswordConfirmation(member);
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

        notificationService.sendChangedPasswordNotification(memberName, memberEmail);
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
    public Page<MemberSummaryResponse> getAllMembers(Pageable pageable) {

        Page<Member> memberList = memberRepository.findAll(pageable);

        if (memberList.isEmpty()) {
            throw new EntityNotFoundException("No member was found!");
        }

        return memberMapper.toMemberSummaryResponsePage(memberList);
    }

    public Page<MemberSummaryResponse> getAllVisibleMembers(Pageable pageable) {

        Page<Member> members = memberRepository.findMembersByProfileHiddenFlagIsFalse(pageable);

        if (members.isEmpty()) {
            throw new EntityNotFoundException("No member was found!");
        }

        return memberMapper.toMemberSummaryResponsePage(members);
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
    public Page<MemberSummaryResponse> getMembersByUsernameAndSpecialization(
            String username,
            String specialization,
            Pageable pageable
                                                                            ) {

        Specification<Member> spec = Specification.where(MemberSpecification.usernameStartsWith(username))
                .and(MemberSpecification.hasSpecialization(specialization))
                .and(MemberSpecification.profileIsNotHidden());

        Page<Member> members = memberRepository.findAll(spec, pageable);

        if (members.isEmpty()) {
            throw new NoSuchUserException("No members found");
        }

        return memberMapper.toMemberSummaryResponsePage(members);
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
    public void deleteMemberByUsername(String username) {

        Member member = memberRepository.findMemberByUsername(username);

        if (member != null) {
            memberRepository.delete(member);
            String name = member.getName();
            String email = member.getEmail();
            notificationService.sendDeletedAccountNotification(name, email);
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

        SpecialistSpecialization specialization = specialistSpecializationService
                .getSpecialistSpecializationEntityById(profileDataRequest.getSpecializationId());
        updatedMember.setSpecialization(specialization);

        memberRepository.save(updatedMember);
        return memberMapper.toMemberFullResponse(updatedMember);
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
