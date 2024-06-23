package org.siri_hate.user_service.controller;


import jakarta.validation.Valid;
import org.siri_hate.user_service.model.dto.request.auth.LoginForm;
import org.siri_hate.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing user operations in the user service API.
 * Provides endpoints for user login.
 */
@RestController
@Validated
@RequestMapping("/api/v1/user_service/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructs a UserController with the provided UserService.
     *
     * @param userService The service responsible for user operations.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for user login.
     *
     * @param loginForm The request body containing user login credentials.
     * @return ResponseEntity with a JWT token for successful login.
     */
    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody @Valid LoginForm loginForm) {
        String token = userService.userLogin(loginForm);
        return ResponseEntity.ok(token);
    }

}
