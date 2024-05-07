package org.siri_hate.user_service.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalData {

    @NotBlank(message = "Name must not be null")
    String name;

    @NotBlank(message = "Phone must not be null")
    String phone;

    @NotBlank(message = "Email must not be null")
    String email;

    @NotNull(message = "Birthday must not be null")
    LocalDate birthday;

}
