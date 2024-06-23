package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.category.ArticleCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.ArticleCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.ArticleCategorySummaryResponse;
import org.siri_hate.main_service.model.entity.category.ArticleCategory;

import java.util.List;

/**
 * This interface defines the contract for the ArticleCategoryService.
 * It provides methods for creating, retrieving, updating, and deleting ArticleCategory entities.
 */
public interface ArticleCategoryService {

    /**
     * This method creates a new ArticleCategory entity from a request DTO and saves it in the database.
     *
     * @param request the ArticleCategoryRequest DTO containing the data for the new ArticleCategory.
     */
    void createArticleCategory(ArticleCategoryRequest request);

    /**
     * This method retrieves all ArticleCategory entities and converts them to summary response DTOs.
     *
     * @return a List of ArticleCategorySummaryResponse DTOs.
     */
    List<ArticleCategorySummaryResponse> getAllArticleCategory();

    /**
     * This method retrieves a ArticleCategory entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the ArticleCategory to retrieve.
     * @return a ArticleCategoryFullResponse DTO.
     */
    ArticleCategoryFullResponse getArticleCategoryById(Long id);

    /**
     * This method retrieves a ArticleCategory entity by its ID.
     *
     * @param id the ID of the ArticleCategory to retrieve.
     * @return a ArticleCategory entity.
     */
    ArticleCategory getArticleCategoryEntityById(Long id);

    /**
     * This method updates an existing ArticleCategory entity with data from a request DTO and saves it in the database.
     *
     * @param id      the ID of the ArticleCategory to update.
     * @param request the ArticleCategoryRequest DTO containing the new data for the ArticleCategory.
     */
    void updateArticleCategory(Long id, ArticleCategoryRequest request);

    /**
     * This method deletes a ArticleCategory entity by its ID from the database.
     *
     * @param id the ID of the ArticleCategory to delete.
     */
    void deleteArticleCategory(Long id);

}