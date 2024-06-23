package org.siri_hate.user_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.siri_hate.user_service.model.dto.request.member.MemberChangeAvatarRequest;
import org.siri_hate.user_service.model.dto.request.member.MemberFullRequest;
import org.siri_hate.user_service.model.dto.request.member.MemberProfileDataRequest;
import org.siri_hate.user_service.model.dto.request.member.MemberRegistrationRequest;
import org.siri_hate.user_service.model.dto.response.member.MemberFullResponse;
import org.siri_hate.user_service.model.dto.response.member.MemberSummaryResponse;
import org.siri_hate.user_service.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.List;

/**
 * Mapper interface for Member entity and its corresponding DTOs.
 * This interface is responsible for mapping between Member entity and DTOs.
 */
@Mapper(componentModel = "spring")
public interface MemberMapper {

    /**
     * Instance of the MemberMapper.
     */
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    /**
     * Maps from MemberRegistrationRequest DTO to Member entity.
     *
     * @param member The MemberRegistrationRequest DTO to be mapped.
     * @return The mapped Member entity.
     */
    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password")
    })
    Member toMemberFromRegistration(MemberRegistrationRequest member);

    /**
     * Maps from Member entity to MemberFullResponse DTO.
     *
     * @param member The Member entity to be mapped.
     * @return The mapped MemberFullResponse DTO.
     */
    @Mapping(source = "specialization.name", target = "specialization")
    MemberFullResponse toMemberFullResponse(Member member);

    /**
     * Maps from Member entity to MemberSummaryResponse DTO.
     *
     * @param member The Member entity to be mapped.
     * @return The mapped MemberSummaryResponse DTO.
     */
    @Mapping(source = "specialization.name", target = "specialization")
    MemberSummaryResponse toMemberSummaryResponse(Member member);

    /**
     * Maps from a list of Member entities to a list of MemberSummaryResponse DTOs.
     *
     * @param members The list of Member entities to be mapped.
     * @return The list of mapped MemberSummaryResponse DTOs.
     */
    List<MemberSummaryResponse> toMemberSummaryResponseList(List<Member> members);

    /**
     * Updates a Member entity with the data from a MemberProfileDataRequest DTO.
     *
     * @param profileDataRequest The MemberProfileDataRequest DTO with the data for the update.
     * @param member The Member entity to be updated.
     * @return The updated Member entity.
     */
    @Mapping(target = "specialization", ignore = true)
    Member memberUpdateProfileData(MemberProfileDataRequest profileDataRequest, @MappingTarget Member member);

    /**
     * Updates a Member entity with the data from a MemberFullRequest DTO.
     *
     * @param memberFullRequest The MemberFullRequest DTO with the data for the update.
     * @param member The Member entity to be updated.
     * @return The updated Member entity.
     */
    @Mapping(target = "specialization", ignore = true)
    Member memberUpdateFullData(MemberFullRequest memberFullRequest, @MappingTarget Member member);

    /**
     * Updates a Member entity's avatar with the data from a MemberChangeAvatarRequest DTO.
     *
     * @param newAvatar The MemberChangeAvatarRequest DTO with the new avatar data.
     * @param member The Member entity to be updated.
     * @return The updated Member entity.
     */
    Member memberUpdateAvatar(MemberChangeAvatarRequest newAvatar, @MappingTarget Member member);

    /**
     * Maps from a Page of Member entities to a Page of MemberSummaryResponse DTOs.
     *
     * @param members The Page of Member entities to be mapped.
     * @return The Page of mapped MemberSummaryResponse DTOs.
     */
    default Page<MemberSummaryResponse> toMemberSummaryResponsePage(Page<Member> members) {
        List<MemberSummaryResponse> summaryResponses = toMemberSummaryResponseList(members.getContent());
        return new PageImpl<>(summaryResponses, members.getPageable(), members.getTotalElements());
    }

}
