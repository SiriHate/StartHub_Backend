package org.siri_hate.chat_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.chat_service.model.User;
import org.siri_hate.chat_service.repository.UserRepository;
import org.siri_hate.chat_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<User> findOrCreateUsers(List<String> usernames) {
        return usernames.stream()
                .map(username -> userRepository.findByUsername(username)
                        .orElseGet(() -> userRepository.save(new User(username))))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getUsersByIds(List<String> userIds) {
        return userRepository.findAllById(userIds);
    }

}
