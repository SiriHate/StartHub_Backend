package org.siri_hate.main_service.service;

import java.util.List;
import org.siri_hate.main_service.model.dto.request.category.ProjectCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategorySummaryResponse;
import org.siri_hate.main_service.model.entity.category.ProjectCategory;

public interface ProjectCategoryService {

  void createProjectCategory(ProjectCategoryRequest request);

  List<ProjectCategorySummaryResponse> getAllProjectCategory();

  ProjectCategoryFullResponse getProjectCategoryById(Long id);

  ProjectCategory getProjectCategoryEntityById(Long id);

  void updateProjectCategory(Long id, ProjectCategoryRequest request);

  void deleteProjectCategory(Long id);
}
