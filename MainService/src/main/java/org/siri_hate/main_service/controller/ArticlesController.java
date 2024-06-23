package org.siri_hate.main_service.controller;

import jakarta.validation.Valid;
import org.siri_hate.main_service.model.dto.request.article.ArticleFullRequest;
import org.siri_hate.main_service.model.dto.response.article.ArticleFullResponse;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a controller for handling requests related to Articles.
 * It provides endpoints for creating, retrieving, updating, and deleting Articles.
 */
@RestController
@Validated
@RequestMapping("/api/v1/main_service/articles")
public class ArticlesController {

    final private ArticleService articleService;

    /**
     * Constructor for the ArticlesController class.
     * It initializes the ArticleService.
     *
     * @param articleService the service for handling business logic related to Articles
     */
    @Autowired
    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * This method handles the creation of a new Article.
     *
     * @param article the request body containing the details of the new Article
     * @return a response entity with a success message and HTTP status code
     */
    @PostMapping
    public ResponseEntity<String> createArticle(@Valid @RequestBody ArticleFullRequest article) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        articleService.createArticle(username, article);
        return new ResponseEntity<>("Article was successfully created", HttpStatus.CREATED);
    }

    /**
     * This method retrieves an Article by its ID.
     *
     * @param id the ID of the Article to retrieve
     * @return a response entity with the requested Article and HTTP status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArticleFullResponse> getArticleById(@PathVariable Long id) {
        ArticleFullResponse article = articleService.getArticleById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    /**
     * This method retrieves all Articles.
     *
     * @param pageable the pagination information
     * @return a response entity with a list of all Articles and HTTP status code
     */
    @GetMapping
    public ResponseEntity<Page<ArticleSummaryResponse>> getAllArticles(@PageableDefault(size = 1) Pageable pageable) {
        Page<ArticleSummaryResponse> articles = articleService.getAllArticles(pageable);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    /**
     * This method retrieves Articles by category and search query.
     *
     * @param category the category of the Articles to retrieve
     * @param query    the search query
     * @param pageable the pagination information
     * @return a response entity with a list of Articles matching the category and search query and HTTP status code
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ArticleSummaryResponse>> getArticlesByCategoryAndSearchQuery(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String query,
            @PageableDefault(size = 10) Pageable pageable
                                                                                           ) {

        Page<ArticleSummaryResponse> newsList = articleService.getArticlesByCategoryAndSearchQuery(
                category,
                query,
                pageable
                                                                                                  );

        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    /**
     * This method updates an existing Article.
     *
     * @param id             the ID of the Article to update
     * @param articleDetails the request body containing the new details of the Article
     * @return a response entity with a success message and HTTP status code
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable Long id, @Valid @RequestBody Article articleDetails) {
        articleService.updateArticle(id, articleDetails);
        return new ResponseEntity<>("Article has been successfully modified", HttpStatus.OK);
    }

    /**
     * This method deletes an existing Article.
     *
     * @param id the ID of the Article to delete
     * @return a response entity with a success message and HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>("Article has been successfully deleted", HttpStatus.NO_CONTENT);
    }

}