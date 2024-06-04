package org.siri_hate.chat_service.service;

import org.siri_hate.chat_service.model.User;
import java.util.List;

public interface UserService {

    List<User> findOrCreateUsers(List<String> usernames);

    List<User> getUsersByIds(List<String> userIds);

}
