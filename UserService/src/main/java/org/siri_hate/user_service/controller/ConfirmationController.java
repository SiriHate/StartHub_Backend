package org.siri_hate.user_service.controller;

import org.siri_hate.user_service.service.ConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/confirmation")
public class ConfirmationController {

    private final ConfirmationService confirmationService;

    @Autowired
    public ConfirmationController(ConfirmationService confirmationService) {
        this.confirmationService = confirmationService;
    }

    @PostMapping("/member/confirm-registration")
    public void confirmMemberRegistration(@RequestBody String token) {
        System.out.println("! "+token);
        confirmationService.checkMemberConfirmationToken(token);
    }

}
