package org.siri_hate.main_service.repository.adapters;

import jakarta.persistence.criteria.Join;
import org.siri_hate.main_service.model.entity.News;
import org.siri_hate.main_service.model.entity.category.NewsCategory;
import org.springframework.data.jpa.domain.Specification;

/**
 * This class provides specifications for querying the News entity.
 * Specifications are predicates that can be combined to create complex queries.
 */
public class NewsSpecification {

    /**
     * Returns a specification that checks if the title of a news starts with a given string.
     *
     * @param title the string that the title should start with
     * @return a specification that can be used in a query
     */
    public static Specification<News> titleStartsWith(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("title"), title + "%");
        };
    }

    /**
     * Returns a specification that checks if a news belongs to a given category.
     *
     * @param category the name of the category
     * @return a specification that can be used in a query
     */
    public static Specification<News> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<News, NewsCategory> categoryJoin = root.join("category");
            return criteriaBuilder.equal(categoryJoin.get("name"), category);
        };
    }

}