package org.siri_hate.notification_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {


    @NotNull(message = "Id should not be null")
    private Long id;


    @NotNull(message = "User id should not be null")
    private Long userId;


    @NotBlank(message = "Name id should not be null")
    private String name;


    @NotBlank(message = "Email id should not be null")
    private String email;


    @NotBlank(message = "Confirmation token id should not be null")
    private String confirmationToken;

}