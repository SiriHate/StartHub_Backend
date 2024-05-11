package org.siri_hate.main_service.controller;

import jakarta.validation.Valid;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.model.entity.News;
import org.siri_hate.main_service.service.ArticleService;
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
@RequestMapping("/api/v1/main/article")
public class ArticlesController {

    final private ArticleService articleService;

    @Autowired
    public ArticlesController (ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<String> createArticle(@Valid @RequestBody  Article article) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        articleService.createArticle(username, article);
        return new ResponseEntity<>("Article was successfully created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<Article>> getArticlesByUsername(@PathVariable String username) {
        List<Article> articles = articleService.getArticlesByUsername(username);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable Long id, @Valid @RequestBody Article articleDetails) {
        articleService.updateArticle(id, articleDetails);
        return new ResponseEntity<>("Article has been successfully modified", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>("Article has been successfully deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find-my-articles")
    public ResponseEntity<List<Article>> findArticlesByUserAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Article> articles = articleService.searchArticlesByOwnerUsername(username);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

}
