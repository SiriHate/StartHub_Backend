package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.siri_hate.user_service.exception.NoSuchUserException;
import org.siri_hate.user_service.model.dto.mapper.ModeratorMapper;
import org.siri_hate.user_service.model.dto.request.moderator.ModeratorFullRequest;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorFullResponse;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorSummaryResponse;
import org.siri_hate.user_service.model.entity.Moderator;
import org.siri_hate.user_service.repository.ModeratorRepository;
import org.siri_hate.user_service.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ModeratorServiceImpl implements ModeratorService {

  private final ModeratorRepository moderatorRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModeratorMapper moderatorMapper;

  @Autowired
  private ModeratorServiceImpl(
      ModeratorRepository moderatorRepository,
      PasswordEncoder passwordEncoder,
      ModeratorMapper moderatorMapper) {
    this.moderatorRepository = moderatorRepository;
    this.passwordEncoder = passwordEncoder;
    this.moderatorMapper = moderatorMapper;
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
  public List<ModeratorSummaryResponse> getAllModerators() {
    List<Moderator> moderators = moderatorRepository.findAll();
    if (moderators.isEmpty()) {
      throw new NoSuchUserException("No moderator was found!");
    }
    return moderatorMapper.toModeratorSummaryResponseList(moderators);
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
    Moderator moderatorToUpdate = moderatorMapper.toModerator(moderator);
    moderatorToUpdate.setId(id);
    Moderator updatedModerator = moderatorRepository.save(moderatorToUpdate);
    return moderatorMapper.toModeratorFullResponse(updatedModerator);
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

  @Override
  @Transactional
  public List<ModeratorFullResponse> searchModerators(String username) {
    List<Moderator> moderators = moderatorRepository.findModeratorByUsernameStartingWithIgnoreCase(username);
    if (moderators.isEmpty()) {
      throw new NoSuchUserException("No moderators found with username starting with: " + username);
    }
    return moderatorMapper.toModeratorFullResponseList(moderators);
  }
}
