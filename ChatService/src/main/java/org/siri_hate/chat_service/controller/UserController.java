package org.siri_hate.chat_service.controller;

import org.siri_hate.chat_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a controller for user-related operations.
 * It handles HTTP requests related to users.
 */
@RestController
@Validated
@RequestMapping("/api/v1/chat_service/users")
public class UserController {

    final private UserService userService;

    final private Authentication authentication;

    /**
     * Constructor for the UserController class.
     * It initializes the UserService and retrieves the Authentication object from the SecurityContextHolder.
     *
     * @param userService the user service
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
    }

}