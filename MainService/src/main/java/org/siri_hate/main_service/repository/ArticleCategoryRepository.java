package org.siri_hate.main_service.repository;

import org.siri_hate.main_service.model.entity.category.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents the repository for the ArticleCategory entity.
 * It extends JpaRepository which provides JPA related methods such as save(), findOne(), findAll(), count(), delete() etc.
 * You can use these methods to perform database operations.
 * It is annotated with @Repository, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Long> {

}