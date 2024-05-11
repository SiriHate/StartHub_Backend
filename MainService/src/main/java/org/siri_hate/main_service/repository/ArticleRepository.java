package org.siri_hate.main_service.repository;

import org.siri_hate.main_service.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findArticleByOwner(String username);
}
