package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.siri_hate.user_service.model.dto.request.auth.LoginForm;
import org.siri_hate.user_service.model.entity.User;
import org.siri_hate.user_service.repository.UserRepository;
import org.siri_hate.user_service.security.JWTService;
import org.siri_hate.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JWTService jwtService;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository,
      AuthenticationManager authenticationManager,
      JWTService jwtService) {
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
  }

  @Override
  public String userLogin(LoginForm loginForm) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginForm.getUsername(), loginForm.getPassword()));
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(loginForm.getUsername(), authentication.getAuthorities());
    } else {
      throw new UsernameNotFoundException("Invalid user request!");
    }
  }

  @Override
  public User findMemberByUsername(String username) {
    Optional<User> userOptional = userRepository.findUserByUsername(username);
    if (userOptional.isEmpty()) {
      throw new EntityNotFoundException("User with username: " + username + " not found!");
    }
    return userOptional.get();
  }
}
