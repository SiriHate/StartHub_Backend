package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.dto.request.LoginForm;
import org.siri_hate.user_service.model.dto.request.moderator.ModeratorFullRequest;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorFullResponse;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ModeratorService {

    void moderatorRegistration(ModeratorFullRequest moderator);

    void moderatorLogin(LoginForm loginForm);

    void moderatorPasswordRecovery(String login);

    Page<ModeratorSummaryResponse> getAllModerators(Pageable pageable);

    Page<ModeratorSummaryResponse> searchModeratorsByUsername(String username, Pageable pageable);

    ModeratorFullResponse getModeratorById(Long id);

    ModeratorFullResponse moderatorUpdate(Long id, ModeratorFullRequest moderator);

    void deleteModeratorById(Long id);

}
