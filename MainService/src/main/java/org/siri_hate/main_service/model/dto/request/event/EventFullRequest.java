package org.siri_hate.main_service.model.dto.request.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class EventFullRequest {

    @NotBlank
    String eventName;

    @NotBlank
    String category;

    @NotBlank
    String eventDescription;

    @NotNull
    LocalDateTime EventDate;

    @NotBlank
    String onlineConferenceLink;

}
