package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.article.ArticleFullRequest;
import org.siri_hate.main_service.model.dto.response.article.ArticleFullResponse;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {

  void createArticle(String username, ArticleFullRequest article);

  ArticleFullResponse getArticleById(Long id);

  Page<ArticleSummaryResponse> getArticlesByCategoryAndSearchQuery(
      String category, String query, Pageable pageable);

  Page<ArticleSummaryResponse> getAllArticles(Pageable pageable);

  void updateArticle(Long id, ArticleFullRequest article);

  void deleteArticle(Long id);
}
