package org.siri_hate.main_service.controller;

import jakarta.validation.Valid;
import org.siri_hate.main_service.model.dto.request.news.NewsFullRequest;
import org.siri_hate.main_service.model.dto.response.news.NewsFullResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.entity.News;
import org.siri_hate.main_service.service.NewsService;
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
 * This class is a controller for handling requests related to News.
 * It provides endpoints for creating, retrieving, updating, and deleting News.
 */
@RestController
@Validated
@RequestMapping("/api/v1/main_service/news")
public class NewsController {

    final private NewsService newsService;

    /**
     * Constructor for the NewsController class.
     * It initializes the NewsService.
     *
     * @param newsService the service for handling business logic related to News
     */
    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * This method handles the creation of a new News.
     *
     * @param news the request body containing the details of the new News
     * @return a response entity with a success message and HTTP status code
     */
    @PostMapping
    public ResponseEntity<String> createNews(@Valid @RequestBody NewsFullRequest news) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        newsService.createNews(username, news);
        return new ResponseEntity<>("News was successfully created", HttpStatus.CREATED);
    }

    /**
     * This method retrieves all News.
     *
     * @param pageable the pagination information
     * @return a response entity with a list of all News and HTTP status code
     */
    @GetMapping
    public ResponseEntity<Page<NewsSummaryResponse>> getAllNews(@PageableDefault(size = 1) Pageable pageable) {
        Page<NewsSummaryResponse> news = newsService.getAllNews(pageable);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    /**
     * This method retrieves a News by its ID.
     *
     * @param id the ID of the News to retrieve
     * @return a response entity with the requested News and HTTP status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<NewsFullResponse> getNewsById(@PathVariable Long id) {
        NewsFullResponse news = newsService.getNewsById(id);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    /**
     * This method retrieves News by category and search query.
     *
     * @param category the category of the News to retrieve
     * @param query    the search query
     * @param pageable the pagination information
     * @return a response entity with a list of News matching the category and search query and HTTP status code
     */
    @GetMapping("/search")
    public ResponseEntity<Page<NewsSummaryResponse>> getNewsByCategoryAndSearchQuery(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String query,
            @PageableDefault(size = 10) Pageable pageable
                                                                                    ) {
        Page<NewsSummaryResponse> newsList = newsService.getNewsByCategoryAndSearchQuery(category, query, pageable);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    /**
     * This method updates an existing News.
     *
     * @param id          the ID of the News to update
     * @param newsDetails the request body containing the new details of the News
     * @return a response entity with a success message and HTTP status code
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateNews(@PathVariable Long id, @Valid @RequestBody News newsDetails) {
        newsService.updateNews(id, newsDetails);
        return new ResponseEntity<>("News has been successfully modified", HttpStatus.OK);
    }

    /**
     * This method deletes an existing News.
     *
     * @param id the ID of the News to delete
     * @return a response entity with a success message and HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>("News has been successfully deleted", HttpStatus.NO_CONTENT);
    }

}