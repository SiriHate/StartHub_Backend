package org.siri_hate.user_service.controller;


import jakarta.validation.Valid;
import org.siri_hate.user_service.model.dto.request.LoginForm;
import org.siri_hate.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/user_service/users")
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody @Valid LoginForm loginForm) {
        String token = userService.userLogin(loginForm);
        return ResponseEntity.ok(token);
    }

}
