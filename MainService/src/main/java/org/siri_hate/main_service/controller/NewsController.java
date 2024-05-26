package org.siri_hate.main_service.controller;

import jakarta.validation.Valid;
import org.siri_hate.main_service.model.dto.request.news.NewsFullRequest;
import org.siri_hate.main_service.model.dto.response.news.NewsFullResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.entity.News;
import org.siri_hate.main_service.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<NewsSummaryResponse>> getAllNews() {
        List<NewsSummaryResponse> news = newsService.getAllNews();
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsFullResponse> getNewsById(@PathVariable Long id) {
        NewsFullResponse news = newsService.getNewsById(id);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/search-by-auth")
    public ResponseEntity<List<NewsSummaryResponse>> findArticlesByUserAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<NewsSummaryResponse> news = newsService.searchNewsByOwnerUsername(username);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/by-category/{category}")
    public ResponseEntity<List<NewsSummaryResponse>> getNewsByCategory(@PathVariable String category) {
        List<NewsSummaryResponse> newsList = newsService.getNewsByCategory(category);
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
