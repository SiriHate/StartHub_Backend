package org.siri_hate.user_service.repository;

import org.siri_hate.user_service.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for the User entity.
 * This interface is used to perform CRUD operations on the User entity in the database.
 * It extends JpaRepository which provides JPA related methods such as save(), findOne(), findAll(), etc.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Method to find a User entity by its username.
     *
     * @param username The username of the User entity to be found.
     * @return An Optional containing the User entity with the given username if it exists, or an empty Optional if it does not.
     */
    Optional<User> findUserByUsername(String username);

}