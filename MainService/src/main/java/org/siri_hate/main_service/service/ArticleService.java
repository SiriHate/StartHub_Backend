package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.article.ArticleFullRequest;
import org.siri_hate.main_service.model.dto.response.article.ArticleFullResponse;
import org.siri_hate.main_service.model.dto.response.article.ArticleSummaryResponse;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.model.entity.News;

import java.util.List;

public interface ArticleService {

    void createArticle(String username, ArticleFullRequest article);

    ArticleFullResponse getArticleById(Long id);

    List<ArticleSummaryResponse> getArticlesByUsername(String username);

    List<ArticleSummaryResponse> getArticlesByTitle(String title);

    List<ArticleSummaryResponse> getAllArticles();

    List<ArticleSummaryResponse> searchArticlesByOwnerUsername(String username);

    void updateArticle(Long id, Article articleDetails);

    void deleteArticle(Long id);

}
