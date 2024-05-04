package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.MismatchedPasswordException;
import org.siri_hate.user_service.exception.UserAlreadyExistsException;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.request.ChangePasswordForm;
import org.siri_hate.user_service.model.request.LoginForm;
import org.siri_hate.user_service.model.request.PersonalData;
import org.siri_hate.user_service.repository.MemberRepository;
import org.siri_hate.user_service.security.JWTService;
import org.siri_hate.user_service.service.ConfirmationService;
import org.siri_hate.user_service.service.MemberService;
import org.siri_hate.user_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;

    final private PasswordEncoder passwordEncoder;

    final private AuthenticationManager authenticationManager;

    final private JWTService jwtService;

    final private ConfirmationService confirmationService;

    final private NotificationService notificationService;

    @Autowired
    private MemberServiceImpl(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JWTService jwtService,
            @Lazy ConfirmationService confirmationService,
            NotificationService notificationService
    ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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
        confirmationService.sendMemberConfirmRegistration(member.getId(), member.getName(), member.getEmail());
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
    }

    @Override
    public String memberLogin(LoginForm loginForm) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginForm.getUsername(), 142);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }

    }

    @Override
    public void memberPasswordRecoveryRequest(String newPassword) {

    }

    @Override
    public void memberPasswordRecoveryConfirmation(String newPassword) {

    }

    @Override
    @Transactional
    public void memberPasswordChange(String username, ChangePasswordForm changePasswordForm) {

        String newPassword = changePasswordForm.getNewPassword();
        String oldPassword = changePasswordForm.getOldPassword();

        Optional<Member> memberOptional = memberRepository.findMemberByUsername(username);

        if (memberOptional.isEmpty()) {
            throw new EntityNotFoundException("Member with id: " + username + " not found!");
        }

        Member member = memberOptional.get();

        if (passwordEncoder.matches(oldPassword, member.getPassword())) {
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
            notificationService.sendDeletedAccountMessage(name, email);
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
            notificationService.sendDeletedAccountMessage(name, email);
        } else {
            throw new EntityNotFoundException("Member with username: " + username + " not found!");
        }

    }

    @Override
    @Transactional
    public void memberChangeAvatar(String username, Byte avatar) {
        Member member = findMemberByUsername(username);
        member.setAvatar(avatar);
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void memberChangePersonalInfo(String username, PersonalData personalData) {
        Member member = findMemberByUsername(username);
        member.setName(personalData.getName());
        member.setPhone(personalData.getPhone());
        member.setEmail(personalData.getEmail());
        member.setBirthDay(personalData.getBirthDay());
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
