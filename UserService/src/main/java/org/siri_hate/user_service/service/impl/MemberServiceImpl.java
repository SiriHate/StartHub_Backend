package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.UserAlreadyExistsException;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.forms.LoginForm;
import org.siri_hate.user_service.repository.MemberRepository;
import org.siri_hate.user_service.security.JWTService;
import org.siri_hate.user_service.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MemberServiceImpl(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JWTService jwtService
    ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public void memberRegistration(Member member) {

        if (memberRepository.findMemberByUsername(member.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Member with provided email or phone already exists!");
        }

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    @Override
    public String memberLogin(LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getLogin(),
                        loginForm.getPassword())
        );
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(loginForm.getLogin());
            return token;
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @Override
    public Member membersPasswordRecovery(String login) {
        return null;
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
