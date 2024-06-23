package org.siri_hate.main_service.repository;

import org.siri_hate.main_service.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface represents the repository for the User entity.
 * It extends JpaRepository which provides JPA related methods such as save(), findOne(), findAll(), count(), delete() etc.
 * You can use these methods to perform database operations.
 * It is annotated with @Repository, indicating that it's a bean and Spring will create an instance of it at runtime.
 * The findUserByUsername method is a custom method that retrieves a User entity by its username.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * This method retrieves a User entity by its username.
     * It returns an Optional that can contain the User entity if it exists, or be empty if it does not.
     *
     * @param username the username of the User entity to retrieve.
     * @return an Optional containing the User entity if it exists, or empty if it does not.
     */
    Optional<User> findUserByUsername(String username);

}