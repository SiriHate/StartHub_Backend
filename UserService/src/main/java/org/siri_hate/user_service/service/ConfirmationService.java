package org.siri_hate.user_service.service;

public interface ConfirmationService {

    void sendRegistrationConfirmation(Long userId, String name, String email);

    void sendChangePasswordConfirmation(Long userId, String name, String email);

    String generateConfirmationToken();

    void checkConfirmationToken(String token);

    Long getUserIdByToken(String token);

    void deleteConfirmationTokenByTokenValue(String token);

    void findConfirmationTokenByTokenValue(String token);

}
