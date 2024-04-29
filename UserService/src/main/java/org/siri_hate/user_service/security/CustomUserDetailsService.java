package org.siri_hate.user_service.security;

import org.siri_hate.user_service.exception.NoSuchUserException;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    private CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            Optional<Member> memberOptional = memberRepository.findMemberByUsername(username);
            Member member = memberOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
            UserDetails user = User.builder().username(username).password(member.getPassword()).roles(member.getRole()).build();

            return user;

        } catch (Exception e) {
            throw new NoSuchUserException(e.getMessage());
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Member member) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = member.getRole();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

}
