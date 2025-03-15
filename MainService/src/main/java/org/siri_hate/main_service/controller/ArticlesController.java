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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/main_service/articles")
public class ArticlesController {

  private final ArticleService articleService;

  @Autowired
  public ArticlesController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @PostMapping
  public ResponseEntity<String> createArticle(@Valid @RequestBody ArticleFullRequest article) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    articleService.createArticle(username, article);
    return new ResponseEntity<>("Article was successfully created", HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ArticleFullResponse> getArticleById(@PathVariable Long id) {
    ArticleFullResponse article = articleService.getArticleById(id);
    return new ResponseEntity<>(article, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<Page<ArticleSummaryResponse>> getAllArticles(
      @PageableDefault(size = 1) Pageable pageable) {
    Page<ArticleSummaryResponse> articles = articleService.getAllArticles(pageable);
    return new ResponseEntity<>(articles, HttpStatus.OK);
  }

  @GetMapping("/search")
  public ResponseEntity<Page<ArticleSummaryResponse>> getArticlesByCategoryAndSearchQuery(
      @RequestParam(required = false) String category,
      @RequestParam(required = false) String query,
      @PageableDefault(size = 10) Pageable pageable) {

    Page<ArticleSummaryResponse> newsList =
        articleService.getArticlesByCategoryAndSearchQuery(category, query, pageable);

    return new ResponseEntity<>(newsList, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateArticle(
      @PathVariable Long id, @Valid @RequestBody Article articleDetails) {
    articleService.updateArticle(id, articleDetails);
    return new ResponseEntity<>("Article has been successfully modified", HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
    articleService.deleteArticle(id);
    return new ResponseEntity<>("Article has been successfully deleted", HttpStatus.NO_CONTENT);
  }
}
