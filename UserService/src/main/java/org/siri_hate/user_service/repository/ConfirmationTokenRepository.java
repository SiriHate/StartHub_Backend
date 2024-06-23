package org.siri_hate.user_service.repository;

import org.siri_hate.user_service.model.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for the ConfirmationToken entity.
 * This interface is used to perform CRUD operations on the ConfirmationToken entity in the database.
 * It extends JpaRepository which provides JPA related methods such as save(), findOne(), findAll(), etc.
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    /**
     * Method to find a ConfirmationToken entity by its token value.
     *
     * @param token The token value of the ConfirmationToken entity to be found.
     * @return An Optional containing the ConfirmationToken entity with the given token value if it exists, or an empty Optional if it does not.
     */
    Optional<ConfirmationToken> findConfirmationTokenByTokenValue(String token);

}