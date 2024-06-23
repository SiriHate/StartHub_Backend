package org.siri_hate.main_service.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.siri_hate.main_service.model.entity.category.ProjectCategory;
import org.siri_hate.main_service.model.dto.request.category.ProjectCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategorySummaryResponse;

import java.util.List;

/**
 * This interface represents a mapper for ProjectCategory.
 * It uses MapStruct, a code generator that simplifies the implementation of mappings between Java bean types.
 */
@Mapper(componentModel = "spring")
public interface ProjectCategoryMapper {

    /**
     * An instance of the ProjectCategoryMapper.
     */
    ProjectCategoryMapper INSTANCE = Mappers.getMapper(ProjectCategoryMapper.class);

    /**
     * Maps from ProjectCategoryRequest to ProjectCategory.
     *
     * @param projectCategoryRequest the ProjectCategoryRequest object
     * @return the mapped ProjectCategory object
     */
    ProjectCategory toProjectCategory(ProjectCategoryRequest projectCategoryRequest);

    /**
     * Maps from ProjectCategory to ProjectCategoryFullResponse.
     *
     * @param projectCategory the ProjectCategory object
     * @return the mapped ProjectCategoryFullResponse object
     */
    ProjectCategoryFullResponse toProjectCategoryFullResponse(ProjectCategory projectCategory);

    /**
     * Maps from a list of ProjectCategory to a list of ProjectCategorySummaryResponse.
     *
     * @param projectCategories the list of ProjectCategory objects
     * @return the mapped list of ProjectCategorySummaryResponse objects
     */
    List<ProjectCategorySummaryResponse> toProjectCategorySummaryResponseList(List<ProjectCategory> projectCategories);

    /**
     * Updates an existing ProjectCategory object from a ProjectCategoryRequest object.
     *
     * @param request         the ProjectCategoryRequest object
     * @param projectCategory the ProjectCategory object to be updated
     * @return the updated ProjectCategory object
     */
    ProjectCategory updateProjectCategoryFromRequest(
            ProjectCategoryRequest request,
            @MappingTarget ProjectCategory projectCategory
                                                    );

}