package org.siri_hate.user_service.service;

import org.siri_hate.user_service.model.forms.LoginForm;
import org.siri_hate.user_service.model.entity.Moderator;
import java.util.List;

public interface ModeratorService {

    public void moderatorRegistration (Moderator moderator);

    public void moderatorLogin(LoginForm loginForm);

    public void moderatorPasswordRecovery(String login);

    public List<Moderator> getAllModerators();

    public Moderator getModeratorById(Long id);

    public Moderator moderatorUpdate(Long id, Moderator moderator);

    public void deleteModeratorById(Long id);

}
