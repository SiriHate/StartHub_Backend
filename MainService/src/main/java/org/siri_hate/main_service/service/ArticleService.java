package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.article.ArticleFullRequest;
import org.siri_hate.main_service.model.dto.response.article.ArticleFullResponse;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    void createArticle(String username, ArticleFullRequest article);

    ArticleFullResponse getArticleById(Long id);

    Page<ArticleSummaryResponse> getArticlesByUsername(String username, Pageable pageable);

    Page<ArticleSummaryResponse> getArticlesByTitle(String title, Pageable pageable);

    Page<ArticleSummaryResponse> getArticlesByCategoryAndSearchQuery(String category, String query, Pageable pageable);

    Page<ArticleSummaryResponse> getAllArticles(Pageable pageable);

    Page<ArticleSummaryResponse> searchArticlesByOwnerUsername(String username, Pageable pageable);

    void updateArticle(Long id, Article articleDetails);

    void deleteArticle(Long id);

}
