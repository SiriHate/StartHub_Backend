package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.entity.User;

public interface UserService {

    User findMemberByUsername(String username);

}
