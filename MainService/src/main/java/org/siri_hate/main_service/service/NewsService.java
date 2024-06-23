package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.news.NewsFullRequest;
import org.siri_hate.main_service.model.dto.response.news.NewsFullResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines the contract for the NewsService.
 * It provides methods for creating, retrieving, updating, and deleting News entities.
 */
public interface NewsService {

    /**
     * This method creates a new News entity from a request DTO and saves it in the database.
     *
     * @param username the username of the User who is creating the News.
     * @param news     the NewsFullRequest DTO containing the data for the new News.
     */
    void createNews(String username, NewsFullRequest news);

    /**
     * This method retrieves a News entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the News to retrieve.
     * @return a NewsFullResponse DTO.
     */
    NewsFullResponse getNewsById(Long id);

    /**
     * This method retrieves all News entities that match a given category and search query, and converts them to summary response DTOs.
     *
     * @param category the category to match.
     * @param query    the search query to match.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of NewsSummaryResponse DTOs.
     */
    Page<NewsSummaryResponse> getNewsByCategoryAndSearchQuery(String category, String query, Pageable pageable);

    /**
     * This method retrieves all News entities and converts them to summary response DTOs.
     *
     * @param pageable the Pageable to use for pagination.
     * @return a Page of NewsSummaryResponse DTOs.
     */
    Page<NewsSummaryResponse> getAllNews(Pageable pageable);

    /**
     * This method updates an existing News entity with data from a provided News entity and saves it in the database.
     *
     * @param id          the ID of the News to update.
     * @param newsDetails the News containing the new data for the News.
     */
    void updateNews(Long id, News newsDetails);

    /**
     * This method deletes a News entity by its ID from the database.
     *
     * @param id the ID of the News to delete.
     */
    void deleteNews(Long id);

}