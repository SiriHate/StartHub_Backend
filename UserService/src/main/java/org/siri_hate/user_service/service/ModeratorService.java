package org.siri_hate.user_service.service;

import java.util.List;
import org.siri_hate.user_service.model.dto.request.moderator.ModeratorFullRequest;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorFullResponse;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ModeratorService {

  void moderatorRegistration(ModeratorFullRequest moderator);

  List<ModeratorSummaryResponse> getAllModerators();

  ModeratorFullResponse getModeratorById(Long id);

  ModeratorFullResponse moderatorUpdate(Long id, ModeratorFullRequest moderator);

  void deleteModeratorById(Long id);

  List<ModeratorFullResponse> searchModerators(String username);
}
