package org.siri_hate.user_service.repository;

import org.siri_hate.user_service.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the Member entity.
 * This interface is used to perform CRUD operations on the Member entity in the database.
 * It extends JpaRepository and JpaSpecificationExecutor which provide JPA related methods such as save(), findOne(), findAll(), etc.
 * and methods for executing Specifications.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    /**
     * Method to find a Member entity by its username.
     *
     * @param username The username of the Member entity to be found.
     * @return The Member entity with the given username.
     */
    Member findMemberByUsername(String username);

    /**
     * Method to find a Member entity by its email.
     *
     * @param email The email of the Member entity to be found.
     * @return The Member entity with the given email.
     */
    Member findMemberByEmail(String email);

    /**
     * Method to find all Member entities whose profiles are not hidden, with pagination.
     *
     * @param pageable The pagination information.
     * @return A Page of Member entities whose profiles are not hidden.
     */
    Page<Member> findMembersByProfileHiddenFlagIsFalse(Pageable pageable);

}