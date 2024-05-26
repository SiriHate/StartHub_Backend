package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.project.ProjectFullRequest;
import org.siri_hate.main_service.model.dto.response.project.ProjectFullResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {

    void createProject(String username, ProjectFullRequest project);

    List<ProjectSummaryResponse> getAllProjects();

    List<ProjectSummaryResponse> searchProjectsByName(String projectName);

    List<ProjectSummaryResponse> searchProjectsByOwnerUsername(String username);

    ProjectFullResponse getProjectById(Long id);

    ProjectFullResponse updateProject(ProjectFullRequest project, Long id);

    void deleteProjectById(String username, Long id);

}
