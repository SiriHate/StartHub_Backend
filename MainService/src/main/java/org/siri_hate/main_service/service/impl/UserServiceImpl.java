package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.exception.NoSuchArticleFoundException;
import org.siri_hate.main_service.exception.NoSuchNewsFoundException;
import org.siri_hate.main_service.exception.NoSuchProjectFoundException;
import org.siri_hate.main_service.exception.NoSuchUserException;
import org.siri_hate.main_service.model.dto.mapper.ArticleMapper;
import org.siri_hate.main_service.model.dto.mapper.NewsMapper;
import org.siri_hate.main_service.model.dto.mapper.ProjectMapper;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.model.entity.News;
import org.siri_hate.main_service.model.entity.Project;
import org.siri_hate.main_service.model.entity.User;
import org.siri_hate.main_service.repository.UserRepository;
import org.siri_hate.main_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * This class implements the UserService interface.
 * It provides methods for creating, retrieving, updating, and deleting User entities.
 * It is annotated with @Service, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    final private ArticleMapper articleMapper;

    final private NewsMapper newsMapper;

    final private ProjectMapper projectMapper;

    /**
     * Constructor for UserServiceImpl.
     *
     * @param userRepository the UserRepository to use for database operations.
     * @param articleMapper  the ArticleMapper to use for converting between DTOs and entities.
     * @param newsMapper     the NewsMapper to use for converting between DTOs and entities.
     * @param projectMapper  the ProjectMapper to use for converting between DTOs and entities.
     */
    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            ArticleMapper articleMapper,
            NewsMapper newsMapper,
            ProjectMapper projectMapper
                          ) {
        this.userRepository = userRepository;
        this.articleMapper = articleMapper;
        this.newsMapper = newsMapper;
        this.projectMapper = projectMapper;
    }

    /**
     * This method retrieves a User entity by its username, or creates a new one if it doesn't exist.
     *
     * @param username the username of the User to retrieve or create.
     * @return a User entity.
     */
    @Override
    public User findOrCreateUser(String username) {
        return userRepository.findUserByUsername(username)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUsername(username);
                    return userRepository.save(newUser);
                });
    }

    /**
     * This method retrieves all Article entities for a given User and converts them to summary response DTOs.
     *
     * @param username the username of the User whose Articles to retrieve.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ArticleSummaryResponse DTOs.
     */
    @Override
    @Transactional
    public Page<ArticleSummaryResponse> getMyArticles(String username, Pageable pageable) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NoSuchUserException("User not found: " + username));

        Set<Article> articles = user.getArticles();

        if (articles.isEmpty()) {
            throw new NoSuchArticleFoundException("No articles found for user: " + username);
        }

        return articleMapper.toArticleSummaryResponsePage(articles, pageable);
    }

    /**
     * This method retrieves all News entities for a given User and converts them to summary response DTOs.
     *
     * @param username the username of the User whose News to retrieve.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of NewsSummaryResponse DTOs.
     */
    @Override
    @Transactional
    public Page<NewsSummaryResponse> getMyNews(String username, Pageable pageable) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NoSuchUserException("User not found: " + username));

        Set<News> news = user.getNews();

        if (news.isEmpty()) {
            throw new NoSuchNewsFoundException("No news found for user: " + username);
        }

        return newsMapper.toNewsSummaryResponsePage(news, pageable);
    }

    /**
     * This method retrieves all Project entities for a given User where the User is the owner, and converts them to summary response DTOs.
     *
     * @param username the username of the User whose Projects to retrieve.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ProjectSummaryResponse DTOs.
     */
    @Override
    @Transactional
    public Page<ProjectSummaryResponse> getProjectsAsOwner(String username, Pageable pageable) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NoSuchUserException("User not found: " + username));


        Set<Project> projects = user.getOwnedProjects();

        if (projects.isEmpty()) {
            throw new NoSuchProjectFoundException("No projects found for user: " + username);
        }

        return projectMapper.toProjectSummaryResponsePage(projects, pageable);
    }

    /**
     * This method retrieves all Project entities for a given User where the User is a member, and converts them to summary response DTOs.
     *
     * @param username the username of the User whose Projects to retrieve.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ProjectSummaryResponse DTOs.
     */
    @Override
    @Transactional
    public Page<ProjectSummaryResponse> getProjectsAsMember(String username, Pageable pageable) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NoSuchUserException("User not found: " + username));


        Set<Project> projects = user.getMemberProjects();

        if (projects.isEmpty()) {
            throw new NoSuchProjectFoundException("No projects found for user: " + username);
        }

        return projectMapper.toProjectSummaryResponsePage(projects, pageable);
    }

}