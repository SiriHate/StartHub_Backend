package org.siri_hate.chat_service.service;

import org.siri_hate.chat_service.model.User;
import java.util.List;

/**
 * This interface represents the service for user operations.
 * It contains methods to find or create users by usernames and get users by ids.
 */
public interface UserService {

    /**
     * This method is used to find or create users by usernames.
     * If a user with a given username does not exist, a new user is created.
     *
     * @param usernames the list of usernames
     * @return a list of User objects corresponding to the usernames
     */
    List<User> findOrCreateUsers(List<String> usernames);

    /**
     * This method is used to get users by their ids.
     *
     * @param userIds the list of user ids
     * @return a list of User objects corresponding to the ids
     */
    List<User> getUsersByIds(List<String> userIds);

}