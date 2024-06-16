package org.siri_hate.user_service.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public class SpecialistSpecializationRequest {

    @NotBlank(message = "Specialization name is required")
    String name;

    public SpecialistSpecializationRequest() { }

    public SpecialistSpecializationRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}