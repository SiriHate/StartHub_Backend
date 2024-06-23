package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.exception.NoSuchArticleFoundException;
import org.siri_hate.main_service.model.dto.mapper.ArticleMapper;
import org.siri_hate.main_service.model.dto.request.article.ArticleFullRequest;
import org.siri_hate.main_service.model.dto.response.article.ArticleFullResponse;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.model.entity.category.ArticleCategory;
import org.siri_hate.main_service.repository.ArticleCategoryRepository;
import org.siri_hate.main_service.repository.ArticleRepository;
import org.siri_hate.main_service.repository.adapters.ArticleSpecification;
import org.siri_hate.main_service.service.ArticleCategoryService;
import org.siri_hate.main_service.service.ArticleService;
import org.siri_hate.main_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This class implements the ArticleService interface.
 * It provides methods for creating, retrieving, updating, and deleting Article entities.
 * It is annotated with @Service, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    final private ArticleRepository articleRepository;

    final private ArticleCategoryService articleCategoryService;

    final private ArticleMapper articleMapper;

    final private UserService userService;

    /**
     * Constructor for ArticleServiceImpl.
     *
     * @param articleRepository      the ArticleRepository to use for database operations.
     * @param articleMapper          the ArticleMapper to use for converting between DTOs and entities.
     * @param articleCategoryService the ArticleCategoryService to use for handling ArticleCategory entities.
     * @param userService            the UserService to use for handling User entities.
     */
    @Autowired
    public ArticleServiceImpl(
            ArticleRepository articleRepository,
            ArticleMapper articleMapper,
            ArticleCategoryService articleCategoryService,
            UserService userService
                             ) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.articleCategoryService = articleCategoryService;
        this.userService = userService;
    }

    /**
     * This method creates a new Article entity from a request DTO and saves it in the database.
     *
     * @param username the username of the User who is creating the Article.
     * @param article  the ArticleFullRequest DTO containing the data for the new Article.
     */
    @Override
    @Transactional
    public void createArticle(String username, ArticleFullRequest article) {
        Article articleEntity = articleMapper.toArticle(article);
        articleEntity.setUser(userService.findOrCreateUser(username));
        articleEntity.setPublicationDate(LocalDate.now());
        articleEntity.setCategory(articleCategoryService.getArticleCategoryEntityById(article.getCategoryId()));
        articleEntity.setModerationPassed(false);
        articleRepository.save(articleEntity);
    }

    /**
     * This method retrieves an Article entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the Article to retrieve.
     * @return an ArticleFullResponse DTO.
     */
    @Override
    @Transactional
    public ArticleFullResponse getArticleById(Long id) {

        Optional<Article> article = articleRepository.findById(id);

        if (article.isEmpty()) {
            throw new NoSuchElementException("Article not found");
        }

        return articleMapper.toArticleFullResponse(article.get());
    }

    /**
     * This method retrieves all Article entities that match a given category and search query, and converts them to summary response DTOs.
     *
     * @param category the category to match.
     * @param query    the search query to match.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ArticleSummaryResponse DTOs.
     */
    @Override
    public Page<ArticleSummaryResponse> getArticlesByCategoryAndSearchQuery(
            String category,
            String query,
            Pageable pageable
                                                                           ) {

        Specification<Article> spec = Specification.where(ArticleSpecification.titleStartsWith(query))
                .and(ArticleSpecification.hasCategory(category));

        Page<Article> articles = articleRepository.findAll(spec, pageable);

        if (articles.isEmpty()) {
            throw new NoSuchArticleFoundException("No articles found");
        }

        return articleMapper.toArticleSummaryResponsePage(articles);
    }

    /**
     * This method retrieves all Article entities and converts them to summary response DTOs.
     *
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ArticleSummaryResponse DTOs.
     */
    @Override
    public Page<ArticleSummaryResponse> getAllArticles(Pageable pageable) {

        Page<Article> articlePage = articleRepository.findAll(pageable);

        if (articlePage.isEmpty()) {
            throw new NoSuchElementException("No articles found");
        }

        return articleMapper.toArticleSummaryResponsePage(articlePage);
    }

    /**
     * This method updates an existing Article entity with data from a provided Article entity and saves it in the database.
     *
     * @param id             the ID of the Article to update.
     * @param articleDetails the Article containing the new data for the Article.
     */
    @Override
    @Transactional
    public void updateArticle(Long id, Article articleDetails) {

        Optional<Article> article = articleRepository.findById(id);

        if (article.isEmpty()) {
            throw new NoSuchElementException("Article with id = " + id + "not found");
        }

        articleRepository.save(article.get());
    }

    /**
     * This method deletes an Article entity by its ID from the database.
     *
     * @param id the ID of the Article to delete.
     */
    @Override
    @Transactional
    public void deleteArticle(Long id) {

        Optional<Article> article = articleRepository.findById(id);

        if (article.isEmpty()) {
            throw new NoSuchElementException("Article with id = " + id + "not found");
        }

        articleRepository.delete(article.get());
    }

}