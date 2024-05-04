package org.siri_hate.user_service.service;

public interface ConfirmationService {

    void sendMemberConfirmRegistration(Long userId, String name, String email);

    void checkMemberConfirmationToken(String token);

}
