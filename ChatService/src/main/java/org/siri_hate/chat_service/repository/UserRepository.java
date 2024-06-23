package org.siri_hate.chat_service.repository;

import org.siri_hate.chat_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import java.util.List;

/**
 * This interface represents a repository for the User model.
 * It extends the MongoRepository interface provided by Spring Data MongoDB.
 * It contains methods to find a user by username and find all users by a list of ids.
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * This method is used to find a user by their username.
     *
     * @param username the username of the user
     * @return an Optional that may contain the User if one with the given username exists
     */
    Optional<User> findByUsername(String username);

    /**
     * This method is used to find all users by a list of ids.
     *
     * @param ids the list of ids
     * @return a list of users that match the given ids
     */
    List<User> findAllById(Iterable<String> ids);
}