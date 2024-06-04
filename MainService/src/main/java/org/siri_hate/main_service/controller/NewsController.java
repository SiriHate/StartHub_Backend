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

@RestController
@Validated
@RequestMapping("/api/v1/main_service/news")
public class NewsController {

    final private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public ResponseEntity<String> createNews(@Valid @RequestBody NewsFullRequest news) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        newsService.createNews(username, news);
        return new ResponseEntity<>("News was successfully created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<NewsSummaryResponse>> getAllNews(@PageableDefault(size = 1) Pageable pageable) {
        Page<NewsSummaryResponse> news = newsService.getAllNews(pageable);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsFullResponse> getNewsById(@PathVariable Long id) {
        NewsFullResponse news = newsService.getNewsById(id);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/user/auth")
    public ResponseEntity<Page<NewsSummaryResponse>> findArticlesByUserAuth(
            @PageableDefault(size = 1) Pageable pageable
                                                                           ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Page<NewsSummaryResponse> news = newsService.searchNewsByOwnerUsername(username, pageable);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<NewsSummaryResponse>> getNewsByCategoryAndSearchQuery(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String query,
            @PageableDefault(size = 10) Pageable pageable
                                                                                    ) {
        Page<NewsSummaryResponse> newsList = newsService.getNewsByCategoryAndSearchQuery(category, query, pageable);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNews(@PathVariable Long id, @Valid @RequestBody News newsDetails) {
        newsService.updateNews(id, newsDetails);
        return new ResponseEntity<>("News has been successfully modified", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>("News has been successfully deleted", HttpStatus.NO_CONTENT);
    }

}
