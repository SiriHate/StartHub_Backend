package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.project.ProjectFullRequest;
import org.siri_hate.main_service.model.dto.response.project.ProjectFullResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines the contract for the ProjectService.
 * It provides methods for creating, retrieving, updating, and deleting Project entities.
 */
public interface ProjectService {

    /**
     * This method creates a new Project entity from a request DTO and saves it in the database.
     *
     * @param username the username of the User who is creating the Project.
     * @param project  the ProjectFullRequest DTO containing the data for the new Project.
     */
    void createProject(String username, ProjectFullRequest project);

    /**
     * This method retrieves all Project entities and converts them to summary response DTOs.
     *
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ProjectSummaryResponse DTOs.
     */
    Page<ProjectSummaryResponse> getAllProjects(Pageable pageable);

    /**
     * This method retrieves all Project entities that match a given category and search query, and converts them to summary response DTOs.
     *
     * @param category the category to match.
     * @param query    the search query to match.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ProjectSummaryResponse DTOs.
     */
    Page<ProjectSummaryResponse> getProjectsByCategoryAndSearchQuery(String category, String query, Pageable pageable);

    /**
     * This method retrieves a Project entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the Project to retrieve.
     * @return a ProjectFullResponse DTO.
     */
    ProjectFullResponse getProjectById(Long id);

    /**
     * This method updates an existing Project entity with data from a provided ProjectFullRequest DTO and saves it in the database.
     *
     * @param project the ProjectFullRequest DTO containing the new data for the Project.
     * @param id      the ID of the Project to update.
     */
    void updateProject(ProjectFullRequest project, Long id);

    /**
     * This method deletes a Project entity by its ID from the database.
     *
     * @param username the username of the User who is deleting the Project.
     * @param id       the ID of the Project to delete.
     */
    void deleteProjectById(String username, Long id);

}