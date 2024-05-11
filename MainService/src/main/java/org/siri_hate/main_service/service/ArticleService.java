package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.model.entity.News;

import java.util.List;

public interface ArticleService {

    void createArticle(String username, Article article);

    Article getArticleById(Long id);

    List<Article> getArticlesByUsername(String username);

    List<Article> getAllArticles();

    List<Article> searchArticlesByOwnerUsername(String username);

    void updateArticle(Long id, Article articleDetails);

    void deleteArticle(Long id);

}
