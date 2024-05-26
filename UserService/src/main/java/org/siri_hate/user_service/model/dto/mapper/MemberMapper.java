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

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password")
    })
    Member toMemberFromRegistration(MemberRegistrationRequest member);

    MemberFullResponse toMemberFullResponse(Member member);

    MemberSummaryResponse toMemberSummaryResponse(Member member);

    List<MemberSummaryResponse> toMemberSummaryResponseList(List<Member> members);

    Member memberUpdateProfileData(MemberProfileDataRequest profileDataRequest, @MappingTarget Member member);

    Member memberUpdateFullData(MemberFullRequest memberFullRequest, @MappingTarget Member member);

    Member memberUpdateAvatar(MemberChangeAvatarRequest newAvatar, @MappingTarget Member member);

}
