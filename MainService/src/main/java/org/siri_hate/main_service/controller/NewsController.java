package org.siri_hate.main_service.controller;

import jakarta.validation.Valid;
import org.siri_hate.main_service.model.entity.News;
import org.siri_hate.main_service.model.entity.Project;
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
@RequestMapping("/api/v1/main/news")
public class NewsController {

    final private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public ResponseEntity<News> createNews(@Valid @RequestBody News news) {
        News createdNews = newsService.createNews(news);
        return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        News news = newsService.getNewsById(id);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/by-category/{category}")
    public ResponseEntity<List<News>> getNewsByCategory(@PathVariable String category) {
        List<News> newsList = newsService.getNewsByCategory(category);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.getAllNews();
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

    @GetMapping("/find-my-news")
    public ResponseEntity<List<News>> findArticlesByUserAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<News> projects = newsService.searchNewsByOwnerUsername(username);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

}
