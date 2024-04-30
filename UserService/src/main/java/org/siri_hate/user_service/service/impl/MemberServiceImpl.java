package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.UserAlreadyExistsException;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.forms.LoginForm;
import org.siri_hate.user_service.repository.MemberRepository;
import org.siri_hate.user_service.security.JWTService;
import org.siri_hate.user_service.service.ConfirmationTokenService;
import org.siri_hate.user_service.service.MemberService;
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

    final private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private MemberServiceImpl(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JWTService jwtService,
            @Lazy ConfirmationTokenService confirmationTokenService
    ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    @Transactional
    public void memberRegistration(Member member) {

        if (memberRepository.findMemberByUsername(member.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Member with provided email or phone already exists!");
        }

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        confirmationTokenService.sendConfirmationToken(member.getId(), member.getName(), member.getEmail());
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
                new UsernamePasswordAuthenticationToken(loginForm.getLogin(), loginForm.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginForm.getLogin());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }

    }

    @Override
    public void memberPasswordRecovery(String newPassword) {

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

        if (memberOptional.isEmpty()) {
            throw new EntityNotFoundException("Member with id: " + id + " not found!");
        }

        memberRepository.delete(memberOptional.get());
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
