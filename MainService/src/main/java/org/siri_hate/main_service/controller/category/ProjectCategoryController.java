package org.siri_hate.main_service.controller.category;

import jakarta.validation.constraints.Positive;
import org.siri_hate.main_service.model.dto.request.category.ProjectCategoryRequest;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategoryFullResponse;
import org.siri_hate.main_service.model.dto.response.category.ProjectCategorySummaryResponse;
import org.siri_hate.main_service.service.ProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller for handling requests related to Project Categories.
 * It provides endpoints for creating, retrieving, updating, and deleting Project Categories.
 */
@RestController
@Validated
@RequestMapping("/api/v1/main_service/project_categories")
public class ProjectCategoryController {

    final private ProjectCategoryService projectCategoryService;

    /**
     * Constructor for the ProjectCategoryController class.
     * It initializes the ProjectCategoryService.
     *
     * @param projectCategoryService the service for handling business logic related to Project Categories
     */
    @Autowired
    public ProjectCategoryController(ProjectCategoryService projectCategoryService) {
        this.projectCategoryService = projectCategoryService;
    }

    /**
     * This method handles the creation of a new Project Category.
     *
     * @param request the request body containing the details of the new Project Category
     * @return a response entity with a success message and HTTP status code
     */
    @PostMapping
    public ResponseEntity<String> createProjectCategory(@RequestBody ProjectCategoryRequest request) {
        projectCategoryService.createProjectCategory(request);
        return new ResponseEntity<>("Project category was successfully created!", HttpStatus.CREATED);
    }

    /**
     * This method retrieves all Project Categories.
     *
     * @return a response entity with a list of all Project Categories and HTTP status code
     */
    @GetMapping
    public ResponseEntity<List<ProjectCategorySummaryResponse>> getAllProjectCategory() {
        List<ProjectCategorySummaryResponse> projectCategories = projectCategoryService.getAllProjectCategory();
        return new ResponseEntity<>(projectCategories, HttpStatus.OK);
    }

    /**
     * This method retrieves a Project Category by its ID.
     *
     * @param id the ID of the Project Category to retrieve
     * @return a response entity with the requested Project Category and HTTP status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectCategoryFullResponse> getProjectCategoryById(
            @Positive @PathVariable Long id
                                                                             ) {
        ProjectCategoryFullResponse projectCategory = projectCategoryService.getProjectCategoryById(id);
        return new ResponseEntity<>(projectCategory, HttpStatus.OK);
    }

    /**
     * This method updates an existing Project Category.
     *
     * @param id      the ID of the Project Category to update
     * @param request the request body containing the new details of the Project Category
     * @return a response entity with a success message and HTTP status code
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProjectCategory(
            @Positive @PathVariable Long id,
            @RequestBody ProjectCategoryRequest request
                                                       ) {
        projectCategoryService.updateProjectCategory(id, request);
        return new ResponseEntity<>("Project category was successfully updated!", HttpStatus.OK);
    }

    /**
     * This method deletes an existing Project Category.
     *
     * @param id the ID of the Project Category to delete
     * @return a response entity with a success message and HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectCategory(@Positive @PathVariable Long id) {
        projectCategoryService.deleteProjectCategory(id);
        return new ResponseEntity<>("Project category was successfully deleted!", HttpStatus.NO_CONTENT);
    }

}