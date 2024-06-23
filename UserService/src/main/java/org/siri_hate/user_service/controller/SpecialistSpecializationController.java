package org.siri_hate.user_service.controller;

import jakarta.validation.constraints.Positive;
import org.siri_hate.user_service.model.dto.request.specialist_specialization.SpecialistSpecializationRequest;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationFullResponse;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationSummaryResponse;
import org.siri_hate.user_service.service.SpecialistSpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller class for managing specialist specialization operations in the user service API.
 * Provides endpoints for creating, retrieving, updating, and deleting specialist specializations.
 */
@RestController
@Validated
@RequestMapping("/api/v1/user_service/specialist_specializations")
public class SpecialistSpecializationController {

    private final SpecialistSpecializationService specialistSpecializationService;

    /**
     * Constructs a SpecialistSpecializationController with the provided SpecialistSpecializationService.
     *
     * @param specialistSpecializationService The service responsible for specialist specialization operations.
     */
    @Autowired
    public SpecialistSpecializationController(SpecialistSpecializationService specialistSpecializationService) {
        this.specialistSpecializationService = specialistSpecializationService;
    }

    /**
     * Endpoint for creating a new specialist specialization.
     *
     * @param request The request body containing details of the specialist specialization.
     * @return ResponseEntity with a success message and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<String> createSpecialistSpecialization(@RequestBody SpecialistSpecializationRequest request) {
        specialistSpecializationService.createSpecialistSpecialization(request);
        return new ResponseEntity<>("Specialist specialization was successfully created!", HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all specialist specializations.
     *
     * @return ResponseEntity with a list of SpecialistSpecializationSummaryResponse and HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<List<SpecialistSpecializationSummaryResponse>> getAllSpecialistSpecialization() {
        List<SpecialistSpecializationSummaryResponse> specialistSpecializations = specialistSpecializationService.getAllSpecialistSpecialization();
        return new ResponseEntity<>(specialistSpecializations, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a specialist specialization by ID.
     *
     * @param id The ID of the specialist specialization to retrieve.
     * @return ResponseEntity with the SpecialistSpecializationFullResponse and HTTP status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SpecialistSpecializationFullResponse> getSpecialistSpecializationById(
            @Positive @PathVariable Long id) {
        SpecialistSpecializationFullResponse specialistSpecialization = specialistSpecializationService.getSpecialistSpecializationById(id);
        return new ResponseEntity<>(specialistSpecialization, HttpStatus.OK);
    }

    /**
     * Endpoint to update a specialist specialization by ID.
     *
     * @param id The ID of the specialist specialization to update.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateSpecialistSpecialization(@Positive @PathVariable Long id) {
        specialistSpecializationService.updateSpecialistSpecialization(id);
        return new ResponseEntity<>("Specialist specialization was successfully updated!", HttpStatus.OK);
    }

    /**
     * Endpoint to delete a specialist specialization by ID.
     *
     * @param id The ID of the specialist specialization to delete.
     * @return ResponseEntity with a success message and HTTP status NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpecialistSpecialization(@Positive @PathVariable Long id) {
        specialistSpecializationService.deleteSpecialistSpecialization(id);
        return new ResponseEntity<>("Specialist specialization was successfully deleted!", HttpStatus.NO_CONTENT);
    }

}
