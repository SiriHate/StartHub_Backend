package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.request.LoginForm;
import org.siri_hate.user_service.model.entity.Moderator;
import java.util.List;

public interface ModeratorService {

    void moderatorRegistration(Moderator moderator);

    void moderatorLogin(LoginForm loginForm);

    void moderatorPasswordRecovery(String login);

    List<Moderator> getAllModerators();

    List<Moderator> searchModeratorsByUsername(String username);

    Moderator getModeratorById(Long id);

    Moderator moderatorUpdate(Long id, Moderator moderator);

    void deleteModeratorById(Long id);

}
