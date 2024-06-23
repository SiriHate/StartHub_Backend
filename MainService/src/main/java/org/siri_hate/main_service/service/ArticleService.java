package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.article.ArticleFullRequest;
import org.siri_hate.main_service.model.dto.response.article.ArticleFullResponse;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines the contract for the ArticleService.
 * It provides methods for creating, retrieving, updating, and deleting Article entities.
 */
public interface ArticleService {

    /**
     * This method creates a new Article entity from a request DTO and saves it in the database.
     *
     * @param username the username of the User who is creating the Article.
     * @param article  the ArticleFullRequest DTO containing the data for the new Article.
     */
    void createArticle(String username, ArticleFullRequest article);

    /**
     * This method retrieves an Article entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the Article to retrieve.
     * @return an ArticleFullResponse DTO.
     */
    ArticleFullResponse getArticleById(Long id);

    /**
     * This method retrieves all Article entities that match a given category and search query, and converts them to summary response DTOs.
     *
     * @param category the category to match.
     * @param query    the search query to match.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ArticleSummaryResponse DTOs.
     */
    Page<ArticleSummaryResponse> getArticlesByCategoryAndSearchQuery(String category, String query, Pageable pageable);

    /**
     * This method retrieves all Article entities and converts them to summary response DTOs.
     *
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ArticleSummaryResponse DTOs.
     */
    Page<ArticleSummaryResponse> getAllArticles(Pageable pageable);

    /**
     * This method updates an existing Article entity with data from a provided Article entity and saves it in the database.
     *
     * @param id             the ID of the Article to update.
     * @param articleDetails the Article containing the new data for the Article.
     */
    void updateArticle(Long id, Article articleDetails);

    /**
     * This method deletes an Article entity by its ID from the database.
     *
     * @param id the ID of the Article to delete.
     */
    void deleteArticle(Long id);

}