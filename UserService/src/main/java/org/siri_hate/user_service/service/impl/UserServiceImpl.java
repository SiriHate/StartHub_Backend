package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.entity.User;
import org.siri_hate.user_service.repository.UserRepository;
import org.siri_hate.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
