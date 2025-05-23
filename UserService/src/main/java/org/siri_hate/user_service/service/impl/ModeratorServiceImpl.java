package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.siri_hate.user_service.exception.NoSuchUserException;
import org.siri_hate.user_service.kafka.KafkaProducerService;
import org.siri_hate.user_service.model.dto.mapper.ModeratorMapper;
import org.siri_hate.user_service.model.dto.request.moderator.ModeratorFullRequest;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorFullResponse;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorSummaryResponse;
import org.siri_hate.user_service.model.entity.Moderator;
import org.siri_hate.user_service.repository.ModeratorRepository;
import org.siri_hate.user_service.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class ModeratorServiceImpl implements ModeratorService {

  private final ModeratorRepository moderatorRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModeratorMapper moderatorMapper;
  private final KafkaProducerService kafkaProducerService;

  @Autowired
  private ModeratorServiceImpl(
      ModeratorRepository moderatorRepository,
      PasswordEncoder passwordEncoder,
      ModeratorMapper moderatorMapper,
      KafkaProducerService kafkaProducerService) {
    this.moderatorRepository = moderatorRepository;
    this.passwordEncoder = passwordEncoder;
    this.moderatorMapper = moderatorMapper;
    this.kafkaProducerService = kafkaProducerService;
  }

  @Override
  @Transactional
  public void moderatorRegistration(ModeratorFullRequest moderator) {
    Moderator moderator1 = new Moderator();
    moderator1.setName(moderator.getName());
    moderator1.setUsername(moderator.getUsername());
    moderator1.setEmployeeId(moderator.getEmployeeId());
    moderator1.setPassword(moderator1.getPassword());
    moderator1.setPassword(passwordEncoder.encode(moderator.getPassword()));
    moderator1.setRole("MODERATOR");
    moderatorRepository.save(moderator1);
  }

  @Override
  @Transactional
  public Page<ModeratorSummaryResponse> getAllModerators(String username, Pageable pageable) {
    Page<Moderator> moderators;
    
    if (username != null && !username.isEmpty()) {
      moderators = moderatorRepository.findModeratorByUsernameStartingWithIgnoreCase(username, pageable);
    } else {
      moderators = moderatorRepository.findAll(pageable);
    }
    
    if (moderators.isEmpty()) {
      throw new NoSuchUserException("No moderators found");
    }
    
    return moderatorMapper.toModeratorSummaryResponsePage(moderators);
  }

  @Override
  @Transactional
  public ModeratorFullResponse getModeratorById(Long id) {
    Optional<Moderator> moderatorOptional = moderatorRepository.findById(id);
    if (moderatorOptional.isEmpty()) {
      throw new EntityNotFoundException("Moderator with id: " + id + " not found!");
    }
    return moderatorMapper.toModeratorFullResponse(moderatorOptional.get());
  }

  @Override
  @Transactional
  public ModeratorFullResponse moderatorUpdate(Long id, ModeratorFullRequest moderator) {
    Optional<Moderator> moderatorOptional = moderatorRepository.findById(id);
    if (moderatorOptional.isEmpty()) {
      throw new EntityNotFoundException("Moderator with id: " + id + " not found!");
    }
    
    Moderator existingModerator = moderatorOptional.get();
    
    if (moderator.getName() != null) {
      existingModerator.setName(moderator.getName());
    }
    
    if (moderator.getUsername() != null) {
      existingModerator.setUsername(moderator.getUsername());
    }
    
    if (moderator.getEmployeeId() != null) {
      existingModerator.setEmployeeId(moderator.getEmployeeId());
    }
    
    if (moderator.getPassword() != null) {
      existingModerator.setPassword(passwordEncoder.encode(moderator.getPassword()));
    }
    
    Moderator updatedModerator = moderatorRepository.save(existingModerator);
    return moderatorMapper.toModeratorFullResponse(updatedModerator);
  }

  @Override
  @Transactional
  public void deleteModeratorById(Long id) {
    Optional<Moderator> moderatorOptional = moderatorRepository.findById(id);
    if (moderatorOptional.isEmpty()) {
      throw new EntityNotFoundException("Moderator with id: " + id + " not found!");
    }
    Moderator moderator = moderatorOptional.get();
    String username = moderator.getUsername();
    moderatorRepository.delete(moderator);
    kafkaProducerService.sendUserDeletionMessage(username);
  }

  @Override
  public void deleteModeratorByUsername(String username) {
    Moderator moderator = moderatorRepository.findModeratorByUsername(username)
            .orElseThrow(() -> new NoSuchElementException("Moderator not found with username: " + username));
    moderatorRepository.delete(moderator);
  }
}
