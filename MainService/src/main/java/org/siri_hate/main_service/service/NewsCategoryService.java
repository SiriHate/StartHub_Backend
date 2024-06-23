package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.category.NewsCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.NewsCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.NewsCategorySummaryResponse;
import org.siri_hate.main_service.model.entity.category.NewsCategory;

import java.util.List;

/**
 * This interface defines the contract for the NewsCategoryService.
 * It provides methods for creating, retrieving, updating, and deleting NewsCategory entities.
 */
public interface NewsCategoryService {

    /**
     * This method creates a new NewsCategory entity from a request DTO and saves it in the database.
     *
     * @param request the NewsCategoryRequest DTO containing the data for the new NewsCategory.
     */
    void createNewsCategory(NewsCategoryRequest request);

    /**
     * This method retrieves a NewsCategory entity by its ID.
     *
     * @param id the ID of the NewsCategory to retrieve.
     * @return a NewsCategory entity.
     */
    NewsCategory getNewsCategoryEntityById(Long id);

    /**
     * This method retrieves all NewsCategory entities and converts them to summary response DTOs.
     *
     * @return a List of NewsCategorySummaryResponse DTOs.
     */
    List<NewsCategorySummaryResponse> getAllNewsCategory();

    /**
     * This method retrieves a NewsCategory entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the NewsCategory to retrieve.
     * @return a NewsCategoryFullResponse DTO.
     */
    NewsCategoryFullResponse getNewsCategoryById(Long id);

    /**
     * This method updates an existing NewsCategory entity with data from a request DTO and saves it in the database.
     *
     * @param id      the ID of the NewsCategory to update.
     * @param request the NewsCategoryRequest DTO containing the new data for the NewsCategory.
     */
    void updateNewsCategory(Long id, NewsCategoryRequest request);

    /**
     * This method deletes a NewsCategory entity by its ID from the database.
     *
     * @param id the ID of the NewsCategory to delete.
     */
    void deleteNewsCategory(Long id);

}