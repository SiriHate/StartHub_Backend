package org.siri_hate.main_service.repository;

import org.siri_hate.main_service.model.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository
    extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
  Page<Article> findByModerationPassed(Boolean moderationPassed, Pageable pageable);
  
  Page<Article> findByModerationPassedTrue(Pageable pageable);
  
  Page<Article> findByModerationPassedFalse(Pageable pageable);
}
