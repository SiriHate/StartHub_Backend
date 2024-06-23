package org.siri_hate.user_service.repository.adapters;

import org.siri_hate.user_service.model.entity.Member;
import org.springframework.data.jpa.domain.Specification;

/**
 * Class providing specifications for querying Member entities.
 * Specifications are predicates that can be used to perform complex queries on the database.
 */
public class MemberSpecification {

    /**
     * Specification that checks if the username of a Member starts with a given string.
     *
     * @param username The string that the username should start with.
     * @return A Specification for querying Members.
     */
    public static Specification<Member> usernameStartsWith(String username) {
        return (root, query, criteriaBuilder) -> {
            if (username == null || username.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("username"), username + "%");
        };
    }

    /**
     * Specification that checks if a Member has a given specialization.
     *
     * @param specialization The specialization that the Member should have.
     * @return A Specification for querying Members.
     */
    public static Specification<Member> hasSpecialization(String specialization) {
        return (root, query, criteriaBuilder) -> {
            if (specialization == null || specialization.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("specialization"), specialization);
        };
    }

    /**
     * Specification that checks if a Member's profile is not hidden.
     *
     * @return A Specification for querying Members.
     */
    public static Specification<Member> profileIsNotHidden() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get("profileHiddenFlag"));
    }

}