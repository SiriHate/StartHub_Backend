package org.siri_hate.user_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.siri_hate.user_service.model.dto.mapper.MemberMapper;
import org.siri_hate.user_service.model.dto.request.member.MemberRegistrationRequest;
import org.siri_hate.user_service.service.impl.MemberServiceImpl;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MemberServiceTest {

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MemberMapper memberMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMember() {

        MemberRegistrationRequest request = new MemberRegistrationRequest();
        request.setName("name");
        request.setEmail("email@example.com");
        request.setPhone("phone");
        request.setBirthday(LocalDate.now());
        request.setUsername("username");
        request.setPassword("password");

        Member member = new Member(
                1L,
                request.getUsername(),
                request.getPassword(),
                "role",
                true,
                "avatarUrl",
                request.getName(),
                null,
                "about",
                request.getEmail(),
                request.getPhone(),
                request.getBirthday(),
                false
        );

        when(memberMapper.toMemberFromRegistration(request)).thenReturn(member);
        when(memberRepository.save(any(Member.class))).thenReturn(member);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");

        memberService.memberRegistration(request);

        verify(memberRepository, times(1)).save(any(Member.class));
    }

}