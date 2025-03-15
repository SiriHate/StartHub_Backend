package org.siri_hate.main_service.model.dto.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.dto.request.category.ProjectCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategorySummaryResponse;
import org.siri_hate.main_service.model.entity.category.ProjectCategory;

@Mapper(componentModel = "spring")
public interface ProjectCategoryMapper {

  ProjectCategoryMapper INSTANCE = Mappers.getMapper(ProjectCategoryMapper.class);

  ProjectCategory toProjectCategory(ProjectCategoryRequest projectCategoryRequest);

  ProjectCategoryFullResponse toProjectCategoryFullResponse(ProjectCategory projectCategory);

  List<ProjectCategorySummaryResponse> toProjectCategorySummaryResponseList(
      List<ProjectCategory> projectCategories);

  ProjectCategory updateProjectCategoryFromRequest(
      ProjectCategoryRequest request, @MappingTarget ProjectCategory projectCategory);
}
