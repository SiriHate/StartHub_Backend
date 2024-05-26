package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.entity.User;
import org.siri_hate.user_service.model.dto.request.LoginForm;

public interface UserService {

    String userLogin(LoginForm loginForm);

    User findMemberByUsername(String username);

}
