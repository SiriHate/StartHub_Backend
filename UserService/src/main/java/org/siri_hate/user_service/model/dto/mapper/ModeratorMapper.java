package org.siri_hate.user_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.user_service.model.dto.request.member.MemberProfileDataRequest;
import org.siri_hate.user_service.model.dto.request.moderator.ModeratorFullRequest;
import org.siri_hate.user_service.model.dto.response.member.MemberSummaryResponse;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorFullResponse;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorSummaryResponse;
import org.siri_hate.user_service.model.entity.Member;
import org.siri_hate.user_service.model.entity.Moderator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModeratorMapper {

    ModeratorMapper INSTANCE = Mappers.getMapper(ModeratorMapper.class);

    Moderator toModerator(ModeratorFullRequest moderatorFullRequest);

    ModeratorFullResponse toModeratorFullResponse(Moderator moderator);

    List<ModeratorFullRequest> toModeratorFullResponseList(List<Moderator> moderators);

    ModeratorSummaryResponse toModeratorSummaryResponse(Moderator moderator);

    List<ModeratorSummaryResponse> toModeratorSummaryResponseList(List<Moderator> moderators);

    Moderator moderatorUpdate(ModeratorFullRequest moderatorFullRequest, @MappingTarget Moderator moderator);

    default Page<ModeratorSummaryResponse> toModeratorSummaryResponsePage(Page<Moderator> moderators) {
        List<ModeratorSummaryResponse> summaryResponses = toModeratorSummaryResponseList(moderators.getContent());
        return new PageImpl<>(summaryResponses, moderators.getPageable(), moderators.getTotalElements());
    }

}
