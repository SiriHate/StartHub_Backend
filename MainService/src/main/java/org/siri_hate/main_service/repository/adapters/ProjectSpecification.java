package org.siri_hate.main_service.repository.adapters;

import jakarta.persistence.criteria.Join;
import org.siri_hate.main_service.model.entity.Project;
import org.siri_hate.main_service.model.entity.category.ProjectCategory;
import org.springframework.data.jpa.domain.Specification;

/**
 * This class provides specifications for querying the Project entity.
 * Specifications are predicates that can be combined to create complex queries.
 */
public class ProjectSpecification {

    /**
     * Returns a specification that checks if the name of a project starts with a given string.
     *
     * @param projectName the string that the project name should start with
     * @return a specification that can be used in a query
     */
    public static Specification<Project> projectNameStartsWith(String projectName) {
        return (root, query, criteriaBuilder) -> {
            if (projectName == null || projectName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("projectName"), projectName + "%");
        };
    }

    /**
     * Returns a specification that checks if a project belongs to a given category.
     *
     * @param category the name of the category
     * @return a specification that can be used in a query
     */
    public static Specification<Project> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Project, ProjectCategory> categoryJoin = root.join("category");
            return criteriaBuilder.equal(categoryJoin.get("name"), category);
        };
    }

}