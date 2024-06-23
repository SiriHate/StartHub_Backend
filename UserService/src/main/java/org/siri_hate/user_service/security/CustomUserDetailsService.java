package org.siri_hate.user_service.security;

import org.siri_hate.user_service.model.entity.User;
import org.siri_hate.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Service class that implements the UserDetailsService interface for Spring Security.
 * This class is used to retrieve user-related data and is used by Spring Security to handle user authentication.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructor for the CustomUserDetailsService class.
     *
     * @param userRepository The UserRepository to be used by this service.
     */
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to load a user by its username.
     * This method is used by Spring Security to authenticate a user.
     *
     * @param username The username of the user to be loaded.
     * @return A UserDetails object containing the user's details.
     * @throws UsernameNotFoundException if the user with the given username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(getAuthorities(user))
                .build();

    }

    /**
     * Method to get the authorities (roles) of a user.
     *
     * @param user The user whose authorities are to be retrieved.
     * @return A collection of GrantedAuthority objects representing the user's authorities.
     */
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

}