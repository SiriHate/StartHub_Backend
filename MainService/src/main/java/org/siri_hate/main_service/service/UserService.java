package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines the contract for the UserService.
 * It provides methods for finding or creating a User, and retrieving the User's articles, news, and projects.
 */
public interface UserService {

    /**
     * This method retrieves a User entity by its username, or creates a new User if one does not exist.
     *
     * @param username the username of the User to find or create.
     * @return a User entity.
     */
    User findOrCreateUser(String username);

    /**
     * This method retrieves all Article entities created by a User and converts them to summary response DTOs.
     *
     * @param username the username of the User whose articles to retrieve.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ArticleSummaryResponse DTOs.
     */
    Page<ArticleSummaryResponse> getMyArticles(String username, Pageable pageable);

    /**
     * This method retrieves all News entities created by a User and converts them to summary response DTOs.
     *
     * @param username the username of the User whose news to retrieve.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of NewsSummaryResponse DTOs.
     */
    Page<NewsSummaryResponse> getMyNews(String username, Pageable pageable);

    /**
     * This method retrieves all Project entities where a User is the owner and converts them to summary response DTOs.
     *
     * @param username the username of the User whose projects to retrieve.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ProjectSummaryResponse DTOs.
     */
    Page<ProjectSummaryResponse> getProjectsAsOwner(String username, Pageable pageable);

    /**
     * This method retrieves all Project entities where a User is a member and converts them to summary response DTOs.
     *
     * @param username the username of the User whose projects to retrieve.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ProjectSummaryResponse DTOs.
     */
    Page<ProjectSummaryResponse> getProjectsAsMember(String username, Pageable pageable);

}