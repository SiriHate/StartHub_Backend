package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.project.ProjectFullRequest;
import org.siri_hate.main_service.model.dto.response.project.ProjectFullResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

  void createProject(String username, ProjectFullRequest project);

  Page<ProjectSummaryResponse> getAllProjects(Pageable pageable);

  Page<ProjectSummaryResponse> getProjectsByCategoryAndSearchQuery(
      String category, String query, Pageable pageable);

  ProjectFullResponse getProjectById(Long id);

  void updateProject(ProjectFullRequest project, Long id);

  void deleteProjectById(String username, Long id);

  boolean toggleProjectLike(String username, Long projectId);

  Long getProjectLikesCount(Long projectId);

  Page<ProjectSummaryResponse> getModeratedProjects(Pageable pageable);

  Page<ProjectSummaryResponse> getUnmoderatedProjects(Pageable pageable);

  void updateProjectModerationStatus(Long projectId, Boolean moderationPassed);
}
