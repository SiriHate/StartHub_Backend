package org.siri_hate.user_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.siri_hate.user_service.model.forms.LoginForm;
import org.siri_hate.user_service.model.entity.Moderator;
import org.siri_hate.user_service.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/moderator")
@Validated
public class ModeratorController {

    final private ModeratorService moderatorService;

    @Autowired
    ModeratorController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> moderatorRegistration(@RequestBody @Valid Moderator moderator) {
        moderatorService.moderatorRegistration(moderator);
        return new ResponseEntity<>("Successful registration", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> moderatorLogin(@RequestBody @Valid LoginForm loginForm) {
        return new ResponseEntity<>("Successful login", HttpStatus.OK);
    }

    @PostMapping("/password_recovery")
    public String moderatorPasswordRecovery(@RequestBody String login) {
        moderatorService.moderatorPasswordRecovery(login);
        return "redirect:/login";
    }

    @PostMapping("/get")
    public ResponseEntity<List<Moderator>> getAllModerators() {
        List<Moderator> moderators = moderatorService.getAllModerators();
        return ResponseEntity.ok(moderators);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Moderator> getModeratorById(
            @PathVariable @Positive(message = "ID should be greater than zero") Long id
    ) {
        Moderator moderator = moderatorService.getModeratorById(id);
        return ResponseEntity.ok(moderator);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Moderator> moderatorUpdate(
            @PathVariable @Positive(message = "ID should be greater than zero") Long id,
            @Valid Moderator moderator
    ) {
        Moderator updatedModerator = moderatorService.moderatorUpdate(id, moderator);
        return ResponseEntity.ok(updatedModerator);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteModeratorById(
            @PathVariable @Positive(message = "ID should be greater than zero") Long id
    ) {
        moderatorService.deleteModeratorById(id);
        return ResponseEntity.noContent().build();
    }

}
