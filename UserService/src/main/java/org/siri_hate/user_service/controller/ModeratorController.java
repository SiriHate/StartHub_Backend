package org.siri_hate.user_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.siri_hate.user_service.model.dto.request.moderator.ModeratorFullRequest;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorFullResponse;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorSummaryResponse;
import org.siri_hate.user_service.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller class for managing moderator-related operations in the user service API.
 * Provides endpoints for moderator registration, retrieval, update, and deletion.
 */
@RestController
@Validated
@RequestMapping("/api/v1/user_service/moderators")
public class ModeratorController {

    private final ModeratorService moderatorService;

    /**
     * Constructs a ModeratorController with the provided ModeratorService.
     *
     * @param moderatorService The service responsible for moderator operations.
     */
    @Autowired
    ModeratorController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    /**
     * Endpoint for registering a new moderator.
     *
     * @param moderator The moderator registration request body.
     * @return ResponseEntity with a success message and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<String> moderatorRegistration(@RequestBody @Valid ModeratorFullRequest moderator) {
        moderatorService.moderatorRegistration(moderator);
        return new ResponseEntity<>("Successful registration", HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all moderators with pagination support.
     *
     * @param pageable Pagination information to specify page size and number.
     * @return ResponseEntity with a page of ModeratorSummaryResponse and HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<Page<ModeratorSummaryResponse>> getAllModerators(
            @PageableDefault(size = 1) Pageable pageable) {
        Page<ModeratorSummaryResponse> moderators = moderatorService.getAllModerators(pageable);
        return new ResponseEntity<>(moderators, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a moderator by ID.
     *
     * @param id The ID of the moderator to retrieve.
     * @return ResponseEntity with the ModeratorFullResponse and HTTP status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModeratorFullResponse> getModeratorById(
            @PathVariable @Positive(message = "ID should be greater than zero") Long id) {
        ModeratorFullResponse moderator = moderatorService.getModeratorById(id);
        return new ResponseEntity<>(moderator, HttpStatus.OK);
    }

    /**
     * Endpoint to update a moderator's details by ID.
     *
     * @param id        The ID of the moderator to update.
     * @param moderator The updated moderator details.
     * @return ResponseEntity with the updated ModeratorFullResponse and HTTP status OK.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ModeratorFullResponse> moderatorUpdate(
            @PathVariable @Positive(message = "ID should be greater than zero") Long id,
            @Valid ModeratorFullRequest moderator) {
        ModeratorFullResponse updatedModerator = moderatorService.moderatorUpdate(id, moderator);
        return new ResponseEntity<>(updatedModerator, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a moderator by ID.
     *
     * @param id The ID of the moderator to delete.
     * @return ResponseEntity with a success message and HTTP status NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteModeratorById(
            @PathVariable @Positive(message = "ID should be greater than zero") Long id) {
        moderatorService.deleteModeratorById(id);
        return new ResponseEntity<>("Successful deletion", HttpStatus.NO_CONTENT);
    }

}
