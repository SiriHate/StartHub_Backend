package org.siri_hate.main_service.model.dto.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

@Mapper(
    componentModel = "spring",
    uses = {UserMapper.class, ProjectMemberMapper.class})
public interface ProjectMapper {

  ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

  Project toProject(ProjectFullRequest project);

  void projectUpdate(ProjectFullRequest request, @MappingTarget Project existingProject);

  @Mapping(source = "category.name", target = "category")
  @Mapping(source = "user", target = "projectOwner")
  @Mapping(source = "members", target = "members")
  @Mapping(target = "likes", expression = "java(getLikesCount(project))")
  @Mapping(target = "hasSurvey", expression = "java(project.getSurvey() != null)")
  ProjectFullResponse toProjectFullResponse(Project project);

  @Mapping(source = "category.name", target = "category")
  @Mapping(target = "likes", expression = "java(getLikesCount(project))")
  ProjectSummaryResponse toProjectSummaryResponse(Project project);

  List<ProjectSummaryResponse> toProjectSummaryResponseList(List<Project> projects);

  default Page<ProjectSummaryResponse> toProjectSummaryResponsePage(Page<Project> projects) {
    List<ProjectSummaryResponse> summaryResponses =
        toProjectSummaryResponseList(projects.getContent());
    return new PageImpl<>(summaryResponses, projects.getPageable(), projects.getTotalElements());
  }

  default Page<ProjectSummaryResponse> toProjectSummaryResponsePage(
      Set<Project> projectSet, Pageable pageable) {
    List<ProjectSummaryResponse> summaryResponses =
        projectSet.stream().map(this::toProjectSummaryResponse).collect(Collectors.toList());
    return new PageImpl<>(summaryResponses, pageable, projectSet.size());
  }

  default Long getLikesCount(Project project) {
    return (long) project.getProjectLikes().size();
  }
}
