package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.dto.request.auth.LoginForm;
import org.siri_hate.user_service.model.entity.User;

public interface UserService {

  String userLogin(LoginForm loginForm);

  User findMemberByUsername(String username);
}
