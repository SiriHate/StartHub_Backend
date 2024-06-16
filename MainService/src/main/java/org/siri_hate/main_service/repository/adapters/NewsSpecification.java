package org.siri_hate.main_service.repository.adapters;

import org.siri_hate.main_service.model.entity.News;
import org.springframework.data.jpa.domain.Specification;

public class NewsSpecification {

    public static Specification<News> titleStartsWith(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("title"), title + "%");
        };
    }

    public static Specification<News> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("category"), category);
        };
    }

}