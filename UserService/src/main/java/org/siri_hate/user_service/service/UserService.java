package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.entity.User;
import org.siri_hate.user_service.model.dto.request.auth.LoginForm;

/**
 * User service interface.
 * This interface defines the contract for user operations.
 * It provides methods for user login and finding a member by username.
 */
public interface UserService {

    /**
     * Logs in a user.
     * This method takes a LoginForm DTO and logs in a user.
     *
     * @param loginForm the login form DTO
     * @return the login response
     */
    String userLogin(LoginForm loginForm);

    /**
     * Finds a member by username.
     * This method finds a member by username.
     *
     * @param username the username of the member
     * @return the User entity
     */
    User findMemberByUsername(String username);

}