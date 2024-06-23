package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.response.project_members.ProjectMembersFullResponse;
import org.siri_hate.main_service.model.dto.response.project_members.ProjectMembersSummaryResponse;
import org.siri_hate.main_service.model.entity.ProjectMember;

/**
 * This interface represents a mapper for ProjectMember.
 * It uses MapStruct, a code generator that simplifies the implementation of mappings between Java bean types.
 * It also uses UserMapper for mapping user related fields.
 */
@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ProjectMemberMapper {

    /**
     * An instance of the ProjectMemberMapper.
     */
    ProjectMemberMapper INSTANCE = Mappers.getMapper(ProjectMemberMapper.class);

    /**
     * Maps from ProjectMember to ProjectMembersFullResponse.
     * The user's id, username, and role are mapped to the id, username, and role fields in the response respectively.
     *
     * @param projectMember the ProjectMember object
     * @return the mapped ProjectMembersFullResponse object
     */
    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "role", target = "role")
    ProjectMembersFullResponse toProjectMembersFullResponse(ProjectMember projectMember);

    /**
     * Maps from ProjectMember to ProjectMembersSummaryResponse.
     * The user's id, username, and role are mapped to the id, username, and role fields in the response respectively.
     *
     * @param projectMember the ProjectMember object
     * @return the mapped ProjectMembersSummaryResponse object
     */
    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "role", target = "role")
    ProjectMembersSummaryResponse toProjectMembersSummaryResponse(ProjectMember projectMember);

}