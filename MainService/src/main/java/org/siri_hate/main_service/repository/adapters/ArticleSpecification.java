package org.siri_hate.main_service.repository.adapters;

import jakarta.persistence.criteria.Join;
import org.siri_hate.main_service.model.entity.Article;
import org.siri_hate.main_service.model.entity.category.ArticleCategory;
import org.springframework.data.jpa.domain.Specification;

/**
 * This class provides specifications for querying the Article entity.
 * Specifications are predicates that can be combined to create complex queries.
 */
public class ArticleSpecification {

    /**
     * Returns a specification that checks if the title of an article starts with a given string.
     *
     * @param title the string that the title should start with
     * @return a specification that can be used in a query
     */
    public static Specification<Article> titleStartsWith(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("title"), title + "%");
        };
    }

    /**
     * Returns a specification that checks if an article belongs to a given category.
     *
     * @param category the name of the category
     * @return a specification that can be used in a query
     */
    public static Specification<Article> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Article, ArticleCategory> categoryJoin = root.join("category");
            return criteriaBuilder.equal(categoryJoin.get("name"), category);
        };
    }
}