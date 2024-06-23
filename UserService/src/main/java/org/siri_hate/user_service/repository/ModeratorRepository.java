package org.siri_hate.user_service.repository;

import org.siri_hate.user_service.model.entity.Moderator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for the Moderator entity.
 * This interface is used to perform CRUD operations on the Moderator entity in the database.
 * It extends JpaRepository which provides JPA related methods such as save(), findOne(), findAll(), etc.
 */
@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {

    /**
     * Method to find a Moderator entity by its username.
     *
     * @param username The username of the Moderator entity to be found.
     * @return An Optional containing the Moderator entity with the given username if it exists, or an empty Optional if it does not.
     */
    Optional<Moderator> findModeratorByUsername(String username);

    /**
     * Method to find a Moderator entity by its username, ignoring case, with pagination.
     *
     * @param username The username of the Moderator entity to be found.
     * @param pageable The pagination information.
     * @return A Page of Moderator entities with the given username, ignoring case.
     */
    Page<Moderator> findModeratorByUsernameStartingWithIgnoreCase(String username, Pageable pageable);

}