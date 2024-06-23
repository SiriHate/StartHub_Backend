package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.request.project.ProjectFullRequest;
import org.siri_hate.main_service.model.dto.response.project.ProjectFullResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.model.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This interface represents a mapper for Project.
 * It uses MapStruct, a code generator that simplifies the implementation of mappings between Java bean types.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ProjectMemberMapper.class})
public interface ProjectMapper {

    /**
     * An instance of the ProjectMapper.
     */
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    /**
     * Maps from ProjectFullRequest to Project.
     *
     * @param project the ProjectFullRequest object
     * @return the mapped Project object
     */
    Project toProject(ProjectFullRequest project);

    /**
     * Maps from Project to ProjectFullResponse.
     * The category name, user, and members are mapped to the category, projectOwner, and members fields in the response respectively.
     *
     * @param project the Project object
     * @return the mapped ProjectFullResponse object
     */
    @Mapping(source = "category.name", target = "category")
    @Mapping(source = "user", target = "projectOwner")
    @Mapping(source = "members", target = "members")
    ProjectFullResponse toProjectFullResponse(Project project);

    /**
     * Maps from Project to ProjectSummaryResponse.
     * The category name is mapped to the category field in the response.
     *
     * @param project the Project object
     * @return the mapped ProjectSummaryResponse object
     */
    @Mapping(source = "category.name", target = "category")
    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    /**
     * Maps from a list of Project to a list of ProjectSummaryResponse.
     *
     * @param projects the list of Project objects
     * @return the mapped list of ProjectSummaryResponse objects
     */
    List<ProjectSummaryResponse> toProjectSummaryResponseList(List<Project> projects);

    /**
     * Updates an existing Project object from a ProjectFullRequest object.
     *
     * @param projectFullRequest the ProjectFullRequest object
     * @param project            the Project object to be updated
     * @return the updated Project object
     */
    Project projectUpdate(ProjectFullRequest projectFullRequest, @MappingTarget Project project);

    /**
     * Converts a Page of Project objects to a Page of ProjectSummaryResponse objects.
     *
     * @param projects the Page of Project objects
     * @return the converted Page of ProjectSummaryResponse objects
     */
    default Page<ProjectSummaryResponse> toProjectSummaryResponsePage(Page<Project> projects) {
        List<ProjectSummaryResponse> summaryResponses = toProjectSummaryResponseList(projects.getContent());
        return new PageImpl<>(summaryResponses, projects.getPageable(), projects.getTotalElements());
    }

    /**
     * Converts a Set of Project objects to a Page of ProjectSummaryResponse objects.
     *
     * @param projectSet the Set of Project objects
     * @param pageable   the Pageable object
     * @return the converted Page of ProjectSummaryResponse objects
     */
    default Page<ProjectSummaryResponse> toProjectSummaryResponsePage(Set<Project> projectSet, Pageable pageable) {
        List<ProjectSummaryResponse> summaryResponses = projectSet.stream()
                .map(this::toProjectSummaryResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(summaryResponses, pageable, projectSet.size());
    }

}