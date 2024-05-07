package org.siri_hate.user_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.siri_hate.user_service.model.request.LoginForm;
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
@Validated
@RequestMapping("/api/v1/users/moderator")
public class ModeratorController {

    final private ModeratorService moderatorService;

    @Autowired
    ModeratorController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @PostMapping
    public ResponseEntity<String> moderatorRegistration(@RequestBody @Valid Moderator moderator) {
        moderatorService.moderatorRegistration(moderator);
        return new ResponseEntity<>("Successful registration", HttpStatus.OK);
    }

    @GetMapping("/get")
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

    @GetMapping("/search")
    public ResponseEntity<List<Moderator>> searchModeratorsByUsername(@RequestParam("username") String username) {
        List<Moderator> moderatorList = moderatorService.searchModeratorsByUsername(username);
        return new ResponseEntity<>(moderatorList, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Moderator> moderatorUpdate(
            @PathVariable @Positive(message = "ID should be greater than zero") Long id,
            @Valid Moderator moderator
    ) {
        Moderator updatedModerator = moderatorService.moderatorUpdate(id, moderator);
        return ResponseEntity.ok(updatedModerator);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteModeratorById(
            @PathVariable @Positive(message = "ID should be greater than zero") Long id
    ) {
        moderatorService.deleteModeratorById(id);
        return ResponseEntity.noContent().build();
    }


}
