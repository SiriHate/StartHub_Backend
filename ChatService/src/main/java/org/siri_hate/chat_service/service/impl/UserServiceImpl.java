package org.siri_hate.chat_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.chat_service.model.User;
import org.siri_hate.chat_service.repository.UserRepository;
import org.siri_hate.chat_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the implementation of the UserService interface.
 * It contains methods to find or create users by usernames and get users by ids.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * The user repository.
     */
    final private UserRepository userRepository;

    /**
     * Constructor for the UserServiceImpl class.
     * It initializes the user repository.
     *
     * @param userRepository the user repository
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method is used to find or create users by usernames.
     * It maps each username to a user from the user repository or a new user, and collects them into a list.
     *
     * @param usernames the usernames
     * @return a list of users
     */
    @Override
    @Transactional
    public List<User> findOrCreateUsers(List<String> usernames) {
        return usernames.stream()
                .map(username -> userRepository.findByUsername(username)
                        .orElseGet(() -> userRepository.save(new User(username))))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get users by ids.
     * It returns all users from the user repository by ids.
     *
     * @param userIds the user ids
     * @return a list of users
     */
    @Override
    public List<User> getUsersByIds(List<String> userIds) {
        return userRepository.findAllById(userIds);
    }

}