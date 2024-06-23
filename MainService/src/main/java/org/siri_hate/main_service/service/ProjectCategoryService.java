package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.category.ProjectCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategorySummaryResponse;
import org.siri_hate.main_service.model.entity.category.ProjectCategory;

import java.util.List;

/**
 * This interface defines the contract for the ProjectCategoryService.
 * It provides methods for creating, retrieving, updating, and deleting ProjectCategory entities.
 */
public interface ProjectCategoryService {

    /**
     * This method creates a new ProjectCategory entity from a request DTO and saves it in the database.
     *
     * @param request the ProjectCategoryRequest DTO containing the data for the new ProjectCategory.
     */
    void createProjectCategory(ProjectCategoryRequest request);

    /**
     * This method retrieves all ProjectCategory entities and converts them to summary response DTOs.
     *
     * @return a List of ProjectCategorySummaryResponse DTOs.
     */
    List<ProjectCategorySummaryResponse> getAllProjectCategory();

    /**
     * This method retrieves a ProjectCategory entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the ProjectCategory to retrieve.
     * @return a ProjectCategoryFullResponse DTO.
     */
    ProjectCategoryFullResponse getProjectCategoryById(Long id);

    /**
     * This method retrieves a ProjectCategory entity by its ID.
     *
     * @param id the ID of the ProjectCategory to retrieve.
     * @return a ProjectCategory entity.
     */
    ProjectCategory getProjectCategoryEntityById(Long id);

    /**
     * This method updates an existing ProjectCategory entity with data from a request DTO and saves it in the database.
     *
     * @param id      the ID of the ProjectCategory to update.
     * @param request the ProjectCategoryRequest DTO containing the new data for the ProjectCategory.
     */
    void updateProjectCategory(Long id, ProjectCategoryRequest request);

    /**
     * This method deletes a ProjectCategory entity by its ID from the database.
     *
     * @param id the ID of the ProjectCategory to delete.
     */
    void deleteProjectCategory(Long id);

}