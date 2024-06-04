package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.request.project.ProjectFullRequest;
import org.siri_hate.main_service.model.dto.response.project.ProjectFullResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.model.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    Project toProject(ProjectFullRequest project);

    ProjectFullResponse toProjectFullResponse(Project project);

    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toProjectSummaryResponseList(List<Project> projects);

    Project projectUpdate(ProjectFullRequest projectFullRequest, @MappingTarget Project project);

    default Page<ProjectSummaryResponse> toProjectSummaryResponsePage(Page<Project> projects) {
        List<ProjectSummaryResponse> summaryResponses = toProjectSummaryResponseList(projects.getContent());
        return new PageImpl<>(summaryResponses, projects.getPageable(), projects.getTotalElements());
    }

}
