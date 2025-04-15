package org.siri_hate.user_service.model.dto.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.user_service.model.dto.request.moderator.ModeratorFullRequest;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorFullResponse;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorSummaryResponse;
import org.siri_hate.user_service.model.entity.Moderator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = "spring")
public interface ModeratorMapper {

  ModeratorMapper INSTANCE = Mappers.getMapper(ModeratorMapper.class);

  Moderator toModerator(ModeratorFullRequest moderatorFullRequest);

  ModeratorFullResponse toModeratorFullResponse(Moderator moderator);

  List<ModeratorFullResponse> toModeratorFullResponseList(List<Moderator> moderators);

  ModeratorSummaryResponse toModeratorSummaryResponse(Moderator moderator);

  List<ModeratorSummaryResponse> toModeratorSummaryResponseList(List<Moderator> moderators);

  Moderator moderatorUpdate(
      ModeratorFullRequest moderatorFullRequest, @MappingTarget Moderator moderator);

  default List<ModeratorSummaryResponse> toModeratorSummaryResponseList(Page<Moderator> moderators) {
    return toModeratorSummaryResponseList(moderators.getContent());
  }
}
