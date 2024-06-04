package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.project.ProjectFullRequest;
import org.siri_hate.main_service.model.dto.response.project.ProjectFullResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    void createProject(String username, ProjectFullRequest project);

    Page<ProjectSummaryResponse> getAllProjects(Pageable pageable);

    Page<ProjectSummaryResponse> searchProjectsByName(String projectName, Pageable pageable);

    Page<ProjectSummaryResponse> searchProjectsByOwnerUsername(String username, Pageable pageable);

    ProjectFullResponse getProjectById(Long id);

    ProjectFullResponse updateProject(ProjectFullRequest project, Long id);

    void deleteProjectById(String username, Long id);

}
