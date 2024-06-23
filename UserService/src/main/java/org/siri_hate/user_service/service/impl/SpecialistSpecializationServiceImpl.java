package org.siri_hate.user_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.user_service.model.dto.mapper.SpecialistSpecializationMapper;
import org.siri_hate.user_service.model.dto.request.specialist_specialization.SpecialistSpecializationRequest;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationFullResponse;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationSummaryResponse;
import org.siri_hate.user_service.model.entity.SpecialistSpecialization;
import org.siri_hate.user_service.repository.SpecialistSpecializationRepository;
import org.siri_hate.user_service.service.SpecialistSpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Specialist specialization service implementation class.
 * This class implements the SpecialistSpecializationService interface and provides the business logic for specialist specialization operations.
 * It uses the SpecialistSpecializationRepository and SpecialistSpecializationMapper to interact with the database and map between DTOs and entities.
 */
@Service
public class SpecialistSpecializationServiceImpl implements SpecialistSpecializationService {

    // Specialist specialization repository instance
    final private SpecialistSpecializationRepository specialistSpecializationRepository;

    // Specialist specialization mapper instance
    final private SpecialistSpecializationMapper specialistSpecializationMapper;

    /**
     * Constructor for the SpecialistSpecializationServiceImpl class.
     * This constructor initializes the SpecialistSpecializationRepository and SpecialistSpecializationMapper.
     *
     * @param specialistSpecializationRepository the specialist specialization repository
     * @param specialistSpecializationMapper the specialist specialization mapper
     */
    @Autowired
    public SpecialistSpecializationServiceImpl(
            SpecialistSpecializationRepository specialistSpecializationRepository,
            SpecialistSpecializationMapper specialistSpecializationMapper
                                              ) {
        this.specialistSpecializationRepository = specialistSpecializationRepository;
        this.specialistSpecializationMapper = specialistSpecializationMapper;
    }

    /**
     * Creates a new specialist specialization.
     * This method maps the SpecialistSpecializationRequest DTO to a SpecialistSpecialization entity and saves the new specialist specialization to the database.
     *
     * @param request the specialist specialization request DTO
     */
    @Override
    @Transactional
    public void createSpecialistSpecialization(@RequestBody SpecialistSpecializationRequest request) {
        SpecialistSpecialization specialistSpecializationEntity = specialistSpecializationMapper
                .toSpecialistSpecialization(request);
        specialistSpecializationRepository.save(specialistSpecializationEntity);
    }

    /**
     * Retrieves all specialist specializations.
     * This method retrieves all specialist specializations from the database and maps them to a list of SpecialistSpecializationSummaryResponse DTOs.
     *
     * @return a list of specialist specialization summary response DTOs
     */
    @Override
    public List<SpecialistSpecializationSummaryResponse> getAllSpecialistSpecialization() {

        List<SpecialistSpecialization> specialistSpecializations = specialistSpecializationRepository.findAll();

        if (specialistSpecializations.isEmpty()) {
            throw new RuntimeException("Specialist specializations not found");
        }

        return specialistSpecializationMapper.toSpecialistSpecializationSummaryResponseList(specialistSpecializations);
    }

    /**
     * Retrieves a specialist specialization by its ID.
     * This method retrieves a specialist specialization from the database by its ID and maps it to a SpecialistSpecializationFullResponse DTO.
     *
     * @param id the ID of the specialist specialization to retrieve
     * @return the specialist specialization full response DTO
     */
    @Override
    public SpecialistSpecializationFullResponse getSpecialistSpecializationById(Long id) {

        SpecialistSpecialization specialistSpecialization = specialistSpecializationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialist specialization not found"));

        return specialistSpecializationMapper.toSpecialistSpecializationFullResponse(specialistSpecialization);

    }

    /**
     * Retrieves a specialist specialization entity by its ID.
     * This method retrieves a specialist specialization entity from the database by its ID.
     *
     * @param id the ID of the specialist specialization entity to retrieve
     * @return the specialist specialization entity
     */
    @Override
    public SpecialistSpecialization getSpecialistSpecializationEntityById(Long id) {
            return specialistSpecializationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Specialist specialization not found"));
    }

    /**
     * Updates an existing specialist specialization.
     * This method retrieves a specialist specialization from the database by its ID, updates it with the information from the SpecialistSpecializationRequest DTO, and saves the updated specialist specialization to the database.
     *
     * @param id the ID of the specialist specialization to update
     * @param request the specialist specialization request DTO
     */
    @Override
    @Transactional
    public void updateSpecialistSpecialization(Long id) {

        SpecialistSpecialization specialistSpecialization = specialistSpecializationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialist specialization not found"));

        specialistSpecializationRepository.save(specialistSpecialization);

    }

    /**
     * Deletes a specialist specialization by its ID.
     * This method retrieves a specialist specialization from the database by its ID and deletes it.
     *
     * @param id the ID of the specialist specialization to delete
     */
    @Override
    @Transactional
    public void deleteSpecialistSpecialization(Long id) {

        SpecialistSpecialization specialistSpecialization = specialistSpecializationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialist specialization not found"));

        specialistSpecializationRepository.delete(specialistSpecialization);

    }

}
