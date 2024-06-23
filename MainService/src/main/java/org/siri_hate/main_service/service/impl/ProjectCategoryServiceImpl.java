package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.exception.NoSuchProjectCategoryException;
import org.siri_hate.main_service.model.entity.category.ProjectCategory;
import org.siri_hate.main_service.model.dto.mapper.ProjectCategoryMapper;
import org.siri_hate.main_service.model.dto.request.category.ProjectCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategorySummaryResponse;
import org.siri_hate.main_service.repository.ProjectCategoryRepository;
import org.siri_hate.main_service.service.ProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class implements the ProjectCategoryService interface.
 * It provides methods for creating, retrieving, updating, and deleting ProjectCategory entities.
 * It is annotated with @Service, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Service
public class ProjectCategoryServiceImpl implements ProjectCategoryService {

    private final ProjectCategoryRepository projectCategoryRepository;
    private final ProjectCategoryMapper projectCategoryMapper;

    /**
     * Constructor for ProjectCategoryServiceImpl.
     *
     * @param projectCategoryRepository the ProjectCategoryRepository to use for database operations.
     * @param projectCategoryMapper     the ProjectCategoryMapper to use for converting between DTOs and entities.
     */
    @Autowired
    public ProjectCategoryServiceImpl(
            ProjectCategoryRepository projectCategoryRepository,
            ProjectCategoryMapper projectCategoryMapper
                                     ) {
        this.projectCategoryRepository = projectCategoryRepository;
        this.projectCategoryMapper = projectCategoryMapper;
    }

    /**
     * This method creates a new ProjectCategory entity from a request DTO and saves it in the database.
     *
     * @param request the ProjectCategoryRequest DTO containing the data for the new ProjectCategory.
     */
    @Override
    @Transactional
    public void createProjectCategory(ProjectCategoryRequest request) {
        ProjectCategory projectCategoryEntity = projectCategoryMapper.toProjectCategory(request);
        projectCategoryRepository.save(projectCategoryEntity);
    }

    /**
     * This method retrieves all ProjectCategory entities and converts them to summary response DTOs.
     *
     * @return a List of ProjectCategorySummaryResponse DTOs.
     */
    @Override
    public List<ProjectCategorySummaryResponse> getAllProjectCategory() {
        List<ProjectCategory> projectCategories = projectCategoryRepository.findAll();

        if (projectCategories.isEmpty()) {
            throw new NoSuchProjectCategoryException("No project categories found!");
        }

        return projectCategoryMapper.toProjectCategorySummaryResponseList(projectCategories);
    }

    /**
     * This method retrieves a ProjectCategory entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the ProjectCategory to retrieve.
     * @return a ProjectCategoryFullResponse DTO.
     */
    @Override
    public ProjectCategoryFullResponse getProjectCategoryById(Long id) {
        ProjectCategory projectCategory = projectCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchProjectCategoryException("No project category with id: " + id));

        return projectCategoryMapper.toProjectCategoryFullResponse(projectCategory);
    }

    /**
     * This method retrieves a ProjectCategory entity by its ID.
     *
     * @param id the ID of the ProjectCategory to retrieve.
     * @return a ProjectCategory entity.
     */
    @Override
    public ProjectCategory getProjectCategoryEntityById(Long id) {
        return projectCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchProjectCategoryException("No project category with id: " + id));
    }

    /**
     * This method updates an existing ProjectCategory entity with data from a request DTO and saves it in the database.
     *
     * @param id      the ID of the ProjectCategory to update.
     * @param request the ProjectCategoryRequest DTO containing the new data for the ProjectCategory.
     */
    @Override
    @Transactional
    public void updateProjectCategory(Long id, ProjectCategoryRequest request) {
        ProjectCategory projectCategory = projectCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchProjectCategoryException("No project category with id: " + id));

        projectCategoryMapper.updateProjectCategoryFromRequest(request, projectCategory);
        projectCategoryRepository.save(projectCategory);
    }

    /**
     * This method deletes a ProjectCategory entity by its ID from the database.
     *
     * @param id the ID of the ProjectCategory to delete.
     */
    @Override
    @Transactional
    public void deleteProjectCategory(Long id) {
        ProjectCategory projectCategory = projectCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchProjectCategoryException("No project category with id: " + id));

        projectCategoryRepository.delete(projectCategory);
    }

}