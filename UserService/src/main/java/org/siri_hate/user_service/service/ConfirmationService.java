package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.entity.Member;

/**
 * Confirmation service interface.
 * This interface defines the contract for confirmation operations.
 * It provides methods for sending registration and password change confirmations, generating and checking confirmation tokens, and managing confirmation tokens.
 */
public interface ConfirmationService {

    /**
     * Sends a registration confirmation.
     * This method sends a registration confirmation to a member.
     *
     * @param member the member entity
     */
    void sendRegistrationConfirmation(Member member);

    /**
     * Sends a password change confirmation.
     * This method sends a password change confirmation to a member.
     *
     * @param member the member entity
     */
    void sendChangePasswordConfirmation(Member member);

    /**
     * Generates a confirmation token.
     * This method generates a confirmation token.
     *
     * @return the confirmation token
     */
    String generateConfirmationToken();

    /**
     * Checks a confirmation token.
     * This method checks a confirmation token.
     *
     * @param token the confirmation token
     */
    void checkConfirmationToken(String token);

    /**
     * Retrieves a user ID by token.
     * This method retrieves a user ID by token.
     *
     * @param token the confirmation token
     * @return the user ID
     */
    Long getUserIdByToken(String token);

    /**
     * Deletes a confirmation token by token value.
     * This method deletes a confirmation token by token value.
     *
     * @param token the confirmation token
     */
    void deleteConfirmationTokenByTokenValue(String token);

    /**
     * Finds a confirmation token by token value.
     * This method finds a confirmation token by token value.
     *
     * @param token the confirmation token
     */
    void findConfirmationTokenByTokenValue(String token);

}