package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.dto.request.moderator.ModeratorFullRequest;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorFullResponse;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Moderator service interface.
 * This interface defines the contract for moderator operations.
 * It provides methods for moderator registration, retrieval, update, and deletion.
 */
public interface ModeratorService {

    /**
     * Registers a moderator.
     * This method takes a ModeratorFullRequest DTO and registers a moderator.
     *
     * @param moderator the moderator full request DTO
     */
    void moderatorRegistration(ModeratorFullRequest moderator);

    /**
     * Retrieves all moderators.
     * This method retrieves all moderators and maps them to ModeratorSummaryResponse DTOs.
     *
     * @param pageable the pagination information
     * @return a page of moderator summary response DTOs
     */
    Page<ModeratorSummaryResponse> getAllModerators(Pageable pageable);

    /**
     * Retrieves a moderator by ID.
     * This method retrieves a moderator by ID and maps it to a ModeratorFullResponse DTO.
     *
     * @param id the ID of the moderator
     * @return the moderator full response DTO
     */
    ModeratorFullResponse getModeratorById(Long id);

    /**
     * Updates a moderator by ID.
     * This method retrieves a moderator by ID, updates the moderator with the information from the ModeratorFullRequest DTO, and saves the updated moderator.
     *
     * @param id the ID of the moderator
     * @param moderator the moderator full request DTO
     * @return the moderator full response DTO
     */
    ModeratorFullResponse moderatorUpdate(Long id, ModeratorFullRequest moderator);

    /**
     * Deletes a moderator by ID.
     * This method retrieves a moderator by ID and deletes it.
     *
     * @param id the ID of the moderator
     */
    void deleteModeratorById(Long id);

}