package org.siri_hate.user_service.controller;

import org.siri_hate.user_service.model.dto.request.tokens.RegistrationTokenRequest;
import org.siri_hate.user_service.service.ConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * Controller class for handling user-related operations in the API.
 * Provides endpoints for checking confirmation tokens and confirming user registrations.
 */
@RestController
@Validated
@RequestMapping("/api/v1/user_service/confirmations")
public class ConfirmationController {

    private final ConfirmationService confirmationService;

    /**
     * Constructs a ConfirmationController with the provided ConfirmationService.
     *
     * @param confirmationService The service responsible for confirmation operations.
     */
    @Autowired
    public ConfirmationController(ConfirmationService confirmationService) {
        this.confirmationService = confirmationService;
    }

    /**
     * Endpoint to check the validity of a confirmation token.
     *
     * @param token The confirmation token to check.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
    @GetMapping("/check_confirmation_token")
    public ResponseEntity<String> checkConfirmationToken(@RequestParam String token) {
        confirmationService.findConfirmationTokenByTokenValue(token);
        return new ResponseEntity<>("Confirmation token found", HttpStatus.OK);
    }

    /**
     * Endpoint to confirm user registration using a registration token.
     *
     * @param registrationTokenRequest The request containing the registration token.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
    @PostMapping("/confirm-registration")
    public ResponseEntity<String> confirmRegistration(@RequestBody RegistrationTokenRequest registrationTokenRequest) {
        confirmationService.checkConfirmationToken(registrationTokenRequest.getToken());
        return new ResponseEntity<>("Registration has been successfully confirmed", HttpStatus.OK);
    }

}
