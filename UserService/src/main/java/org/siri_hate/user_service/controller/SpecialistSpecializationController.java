package org.siri_hate.user_service.controller;

import jakarta.validation.constraints.Positive;
import java.util.List;
import org.siri_hate.user_service.model.dto.request.specialization.SpecialistSpecializationRequest;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationFullResponse;
import org.siri_hate.user_service.model.dto.response.specialization.SpecialistSpecializationSummaryResponse;
import org.siri_hate.user_service.service.SpecialistSpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/user_service/specialist_specializations")
public class SpecialistSpecializationController {

  private final SpecialistSpecializationService specialistSpecializationService;

  @Autowired
  public SpecialistSpecializationController(
      SpecialistSpecializationService specialistSpecializationService) {
    this.specialistSpecializationService = specialistSpecializationService;
  }

  @PostMapping
  public ResponseEntity<String> createSpecialistSpecialization(
      @RequestBody SpecialistSpecializationRequest request) {
    specialistSpecializationService.createSpecialistSpecialization(request);
    return new ResponseEntity<>(
        "Specialist specialization was successfully created!", HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<SpecialistSpecializationSummaryResponse>>
      getAllSpecialistSpecialization() {
    List<SpecialistSpecializationSummaryResponse> specialistSpecializations =
        specialistSpecializationService.getAllSpecialistSpecialization();
    return new ResponseEntity<>(specialistSpecializations, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SpecialistSpecializationFullResponse> getSpecialistSpecializationById(
      @Positive @PathVariable Long id) {
    SpecialistSpecializationFullResponse specialistSpecialization =
        specialistSpecializationService.getSpecialistSpecializationById(id);
    return new ResponseEntity<>(specialistSpecialization, HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<String> updateSpecialistSpecialization(@Positive @PathVariable Long id) {
    specialistSpecializationService.updateSpecialistSpecialization(id);
    return new ResponseEntity<>(
        "Specialist specialization was successfully updated!", HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSpecialistSpecialization(@Positive @PathVariable Long id) {
    specialistSpecializationService.deleteSpecialistSpecialization(id);
    return new ResponseEntity<>(
        "Specialist specialization was successfully deleted!", HttpStatus.NO_CONTENT);
  }
}
