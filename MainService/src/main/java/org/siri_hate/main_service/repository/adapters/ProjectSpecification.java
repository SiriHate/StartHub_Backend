package org.siri_hate.main_service.repository.adapters;

import org.siri_hate.main_service.model.entity.Project;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecification {

    public static Specification<Project> projectNameStartsWith(String projectName) {
        return (root, query, criteriaBuilder) -> {
            if (projectName == null || projectName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("projectName"), projectName + "%");
        };
    }

    public static Specification<Project> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("category"), category);
        };
    }
    
}
