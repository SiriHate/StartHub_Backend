package org.siri_hate.user_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.user_service.model.dto.request.moderator.ModeratorFullRequest;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorFullResponse;
import org.siri_hate.user_service.model.dto.response.moderator.ModeratorSummaryResponse;
import org.siri_hate.user_service.model.entity.Moderator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

/**
 * Mapper interface for the Moderator entity and its related DTOs.
 * This interface is used to map between the Moderator entity and its related DTOs.
 */
@Mapper(componentModel = "spring")
public interface ModeratorMapper {

    /**
     * Instance of the ModeratorMapper.
     */
    ModeratorMapper INSTANCE = Mappers.getMapper(ModeratorMapper.class);

    /**
     * Maps from ModeratorFullRequest DTO to Moderator entity.
     *
     * @param moderatorFullRequest The ModeratorFullRequest DTO.
     * @return The mapped Moderator entity.
     */
    Moderator toModerator(ModeratorFullRequest moderatorFullRequest);

    /**
     * Maps from Moderator entity to ModeratorFullResponse DTO.
     *
     * @param moderator The Moderator entity.
     * @return The mapped ModeratorFullResponse DTO.
     */
    ModeratorFullResponse toModeratorFullResponse(Moderator moderator);

    /**
     * Maps from a list of Moderator entities to a list of ModeratorFullRequest DTOs.
     *
     * @param moderators The list of Moderator entities.
     * @return The list of mapped ModeratorFullRequest DTOs.
     */
    List<ModeratorFullRequest> toModeratorFullResponseList(List<Moderator> moderators);

    /**
     * Maps from Moderator entity to ModeratorSummaryResponse DTO.
     *
     * @param moderator The Moderator entity.
     * @return The mapped ModeratorSummaryResponse DTO.
     */
    ModeratorSummaryResponse toModeratorSummaryResponse(Moderator moderator);

    /**
     * Maps from a list of Moderator entities to a list of ModeratorSummaryResponse DTOs.
     *
     * @param moderators The list of Moderator entities.
     * @return The list of mapped ModeratorSummaryResponse DTOs.
     */
    List<ModeratorSummaryResponse> toModeratorSummaryResponseList(List<Moderator> moderators);

    /**
     * Updates a Moderator entity with the data from a ModeratorFullRequest DTO.
     *
     * @param moderatorFullRequest The ModeratorFullRequest DTO.
     * @param moderator The Moderator entity to be updated.
     * @return The updated Moderator entity.
     */
    Moderator moderatorUpdate(ModeratorFullRequest moderatorFullRequest, @MappingTarget Moderator moderator);

    /**
     * Maps from a Page of Moderator entities to a Page of ModeratorSummaryResponse DTOs.
     *
     * @param moderators The Page of Moderator entities.
     * @return The Page of mapped ModeratorSummaryResponse DTOs.
     */
    default Page<ModeratorSummaryResponse> toModeratorSummaryResponsePage(Page<Moderator> moderators) {
        List<ModeratorSummaryResponse> summaryResponses = toModeratorSummaryResponseList(moderators.getContent());
        return new PageImpl<>(summaryResponses, moderators.getPageable(), moderators.getTotalElements());
    }

}