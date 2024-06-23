package org.siri_hate.notification_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * This class represents a confirmation token that is used for user verification.
 * It includes the user's id, name, email, and the confirmation token itself.
 *
 * @author SiriHate
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {

    /**
     * The unique identifier for the confirmation token.
     * This field is mandatory and should not be null.
     */
    @NotNull(message = "Id should not be null")
    private Long id;

    /**
     * The unique identifier for the user associated with this confirmation token.
     * This field is mandatory and should not be null.
     */
    @NotNull(message = "User id should not be null")
    private Long userId;

    /**
     * The name of the user associated with this confirmation token.
     * This field is mandatory and should not be null or blank.
     */
    @NotBlank(message = "Name id should not be null")
    private String name;

    /**
     * The email of the user associated with this confirmation token.
     * This field is mandatory and should not be null or blank.
     */
    @NotBlank(message = "Email id should not be null")
    private String email;

    /**
     * The confirmation token string.
     * This field is mandatory and should not be null or blank.
     */
    @NotBlank(message = "Confirmation token id should not be null")
    private String confirmationToken;

}