package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.UserAlreadyExistsException;
import org.siri_hate.user_service.model.forms.LoginForm;
import org.siri_hate.user_service.model.entity.Moderator;
import org.siri_hate.user_service.repository.ModeratorRepository;
import org.siri_hate.user_service.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ModeratorServiceImpl implements ModeratorService {

    final private ModeratorRepository moderatorRepository;

    final private PasswordEncoder passwordEncoder;

    @Autowired
    private ModeratorServiceImpl(
            ModeratorRepository moderatorRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.moderatorRepository = moderatorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void moderatorRegistration(Moderator moderator) {

        if (moderatorRepository.findModeratorByUsername(moderator.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Moderator with provided employee login already exists");
        }

        moderator.setPassword(passwordEncoder.encode(moderator.getPassword()));
        moderatorRepository.save(moderator);
    }

    @Override
    public void moderatorLogin(LoginForm loginForm) {

    }

    @Override
    public void moderatorPasswordRecovery(String login) {

    }

    @Override
    @Transactional
    public List<Moderator> getAllModerators() {

        List<Moderator> moderatorList = moderatorRepository.findAll();

        if (moderatorList.isEmpty()) {
            throw new EntityNotFoundException("No moderator was found!");
        }

        return moderatorList;
    }

    @Override
    @Transactional
    public Moderator getModeratorById(Long id) {

        Optional<Moderator> moderatorOptional = moderatorRepository.findById(id);

        if (moderatorOptional.isEmpty()) {
            throw new EntityNotFoundException("Moderator with id: " + id + " not found!");
        }

        return moderatorOptional.get();
    }

    @Override
    @Transactional
    public Moderator moderatorUpdate(Long id, Moderator moderator) {

        Optional<Moderator> moderatorOptional = moderatorRepository.findById(id);

        if (moderatorOptional.isEmpty()) {
            throw new EntityNotFoundException("Moderator with id: " + id + " not found!");
        }

        moderator.setId(id);
        return moderatorRepository.save(moderator);
    }

    @Override
    @Transactional
    public void deleteModeratorById(Long id) {

        Optional<Moderator> moderatorOptional = moderatorRepository.findById(id);

        if (moderatorOptional.isEmpty()) {
            throw new EntityNotFoundException("Moderator with id: " + id + " not found!");
        }

        moderatorRepository.delete(moderatorOptional.get());
    }

}
