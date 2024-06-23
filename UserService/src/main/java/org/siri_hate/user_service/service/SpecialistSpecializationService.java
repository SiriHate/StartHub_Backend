package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.dto.request.specialist_specialization.SpecialistSpecializationRequest;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationFullResponse;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationSummaryResponse;
import org.siri_hate.user_service.model.entity.SpecialistSpecialization;

import java.util.List;

/**
 * Specialist Specialization service interface.
 * This interface defines the contract for specialist specialization operations.
 * It provides methods for creating, retrieving, updating, and deleting specialist specializations.
 */
public interface SpecialistSpecializationService {

    /**
     * Creates a specialist specialization.
     * This method takes a SpecialistSpecializationRequest DTO and creates a specialist specialization.
     *
     * @param request the specialist specialization request DTO
     */
    void createSpecialistSpecialization(SpecialistSpecializationRequest request);

    /**
     * Retrieves all specialist specializations.
     * This method retrieves all specialist specializations and maps them to SpecialistSpecializationSummaryResponse DTOs.
     *
     * @return a list of specialist specialization summary response DTOs
     */
    List<SpecialistSpecializationSummaryResponse> getAllSpecialistSpecialization();

    /**
     * Retrieves a specialist specialization by ID.
     * This method retrieves a specialist specialization by ID and maps it to a SpecialistSpecializationFullResponse DTO.
     *
     * @param id the ID of the specialist specialization
     * @return the specialist specialization full response DTO
     */
    SpecialistSpecializationFullResponse getSpecialistSpecializationById(Long id);

    /**
     * Retrieves a specialist specialization entity by ID.
     * This method retrieves a specialist specialization entity by ID.
     *
     * @param id the ID of the specialist specialization
     * @return the specialist specialization entity
     */
    SpecialistSpecialization getSpecialistSpecializationEntityById(Long id);

    /**
     * Updates a specialist specialization by ID.
     * This method updates a specialist specialization by ID.
     *
     * @param id the ID of the specialist specialization
     */
    void updateSpecialistSpecialization(Long id);

    /**
     * Deletes a specialist specialization by ID.
     * This method deletes a specialist specialization by ID.
     *
     * @param id the ID of the specialist specialization
     */
    void deleteSpecialistSpecialization(Long id);

}