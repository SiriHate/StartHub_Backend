package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.entity.ConfirmationToken;

public interface ConfirmationTokenService {

    public void sendConfirmationToken(Long userId, String name, String email);

    public void checkMemberConfirmationToken(String token);

}
