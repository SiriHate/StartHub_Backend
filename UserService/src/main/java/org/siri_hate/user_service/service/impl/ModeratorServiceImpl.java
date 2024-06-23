package org.siri_hate.user_service.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.siri_hate.user_service.exception.NoSuchUserException;
import org.siri_hate.user_service.model.entity.Moderator;
import org.siri_hate.user_service.model.dto.request.moderator.ModeratorFullRequest;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorFullResponse;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorSummaryResponse;
import org.siri_hate.user_service.model.dto.mapper.ModeratorMapper;
import org.siri_hate.user_service.repository.ModeratorRepository;
import org.siri_hate.user_service.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Moderator service implementation class.
 * This class implements the ModeratorService interface and provides the business logic for moderator operations.
 * It uses the ModeratorRepository, PasswordEncoder, and ModeratorMapper to interact with the database, manage moderators, and map between DTOs and entities.
 */
@Service
public class ModeratorServiceImpl implements ModeratorService {

    // Moderator repository instance
    final private ModeratorRepository moderatorRepository;

    // Password encoder instance
    final private PasswordEncoder passwordEncoder;

    // Moderator mapper instance
    final private ModeratorMapper moderatorMapper;

    /**
     * Constructor for the ModeratorServiceImpl class.
     *
     * @param moderatorRepository The ModeratorRepository to be used by this service.
     * @param passwordEncoder The PasswordEncoder to be used by this service.
     * @param moderatorMapper The ModeratorMapper to be used by this service.
     */
    @Autowired
    private ModeratorServiceImpl(
            ModeratorRepository moderatorRepository,
            PasswordEncoder passwordEncoder,
            ModeratorMapper moderatorMapper
    ) {
        this.moderatorRepository = moderatorRepository;
        this.passwordEncoder = passwordEncoder;
        this.moderatorMapper = moderatorMapper;
    }

    /**
     * Handles moderator registration.
     * This method maps the ModeratorFullRequest DTO to a Moderator entity, encodes the password, and saves the new moderator to the database.
     *
     * @param moderator the moderator full request DTO
     */
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

    /**
     * Handles getting all moderators.
     * This method retrieves all moderators from the database and maps them to a page of ModeratorSummaryResponse DTOs.
     *
     * @param pageable The pagination information.
     * @return A page of ModeratorSummaryResponse DTOs containing the moderators.
     */
    @Override
    @Transactional
    public Page<ModeratorSummaryResponse> getAllModerators(Pageable pageable) {

        Page<Moderator> moderators = moderatorRepository.findAll(pageable);

        if (moderators.isEmpty()) {
            throw new NoSuchUserException("No moderator was found!");
        }

        return moderatorMapper.toModeratorSummaryResponsePage(moderators);
    }

    /**
     * Handles getting a moderator by ID.
     * This method retrieves a moderator by ID from the database and maps it to a ModeratorFullResponse DTO.
     *
     * @param id The ID of the moderator to be retrieved.
     * @return The ModeratorFullResponse DTO containing the moderator.
     */
    @Override
    @Transactional
    public ModeratorFullResponse getModeratorById(Long id) {
        Optional<Moderator> moderatorOptional = moderatorRepository.findById(id);

        if (moderatorOptional.isEmpty()) {
            throw new EntityNotFoundException("Moderator with id: " + id + " not found!");
        }

        return moderatorMapper.toModeratorFullResponse(moderatorOptional.get());
    }

    /**
     * Handles updating a moderator.
     * This method updates a moderator in the database by ID, using the data from the ModeratorFullRequest DTO.
     *
     * @param id The ID of the moderator to be updated.
     * @param moderator The ModeratorFullRequest DTO containing the updated moderator data.
     * @return The ModeratorFullResponse DTO containing the updated moderator.
     */
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

    /**
     * Handles deleting a moderator by ID.
     * This method deletes a moderator by ID from the database.
     *
     * @param id The ID of the moderator to be deleted.
     */
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
