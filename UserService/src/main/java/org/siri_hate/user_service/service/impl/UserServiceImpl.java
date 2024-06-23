package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.siri_hate.user_service.model.entity.User;
import org.siri_hate.user_service.model.dto.request.auth.LoginForm;
import org.siri_hate.user_service.repository.UserRepository;
import org.siri_hate.user_service.security.JWTService;
import org.siri_hate.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User service implementation class.
 * This class implements the UserService interface and provides the business logic for user operations.
 * It uses the UserRepository, AuthenticationManager, and JWTService to interact with the database, manage user authentication, and generate JWT tokens respectively.
 */
@Service
public class UserServiceImpl implements UserService {

    // User repository instance
    private final UserRepository userRepository;

    // Authentication manager instance
    final private AuthenticationManager authenticationManager;

    // JWT service instance
    final private JWTService jwtService;

    /**
     * Constructor for the UserServiceImpl class.
     * This constructor initializes the UserRepository, AuthenticationManager, and JWTService.
     *
     * @param userRepository the user repository
     * @param authenticationManager the authentication manager
     * @param jwtService the JWT service
     */
    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            JWTService jwtService
                          ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /**
     * Handles user login.
     * This method authenticates a user using the AuthenticationManager and generates a JWT token if the authentication is successful.
     *
     * @param loginForm the login form DTO
     * @return the JWT token
     * @throws UsernameNotFoundException if the authentication is not successful
     */
    @Override
    public String userLogin(LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginForm.getUsername(), authentication.getAuthorities());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }

    }

    /**
     * Retrieves a user by username.
     * This method retrieves a user from the database by username.
     *
     * @param username the username of the user
     * @return the user entity
     * @throws EntityNotFoundException if the user is not found
     */
    @Override
    public User findMemberByUsername(String username) {

        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with username: " + username + " not found!");
        }

        return userOptional.get();
    }

}