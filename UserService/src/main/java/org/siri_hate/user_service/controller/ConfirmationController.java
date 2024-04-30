package org.siri_hate.user_service.controller;

import org.siri_hate.user_service.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/confirmation")
public class ConfirmationController {

    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public ConfirmationController(ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
    }

    @GetMapping("/member/confirm-registration/{token}")
    public void confirmRegistration(@PathVariable String token) {
        confirmationTokenService.checkMemberConfirmationToken(token);
    }

}
