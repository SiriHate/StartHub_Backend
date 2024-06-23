package org.siri_hate.chat_service.controller;

import org.siri_hate.chat_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/chat_service/users")
public class UserController {

    final private UserService userService;

    final private Authentication authentication;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
    }

}
