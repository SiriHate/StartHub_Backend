package org.siri_hate.main_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.siri_hate.main_service.model.dto.request.project.ProjectFullRequest;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectFullResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a controller for handling requests related to Projects.
 * It provides endpoints for creating, retrieving, updating, and deleting Projects.
 */
@RestController
@Validated
@RequestMapping("/api/v1/main_service/projects")
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Constructor for the ProjectController class.
     * It initializes the ProjectService.
     *
     * @param projectService the service for handling business logic related to Projects
     */
    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * This method handles the creation of a new Project.
     *
     * @param project the request body containing the details of the new Project
     * @return a response entity with a success message and HTTP status code
     */
    @PostMapping
    public ResponseEntity<String> createProject(@RequestBody @Valid ProjectFullRequest project) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        projectService.createProject(username, project);
        return new ResponseEntity<>("Project has been successfully created", HttpStatus.CREATED);
    }

    /**
     * This method retrieves all Projects.
     *
     * @param pageable the pagination information
     * @return a response entity with a list of all Projects and HTTP status code
     */
    @GetMapping
    public ResponseEntity<Page<ProjectSummaryResponse>> getAllProjects(@PageableDefault(size = 1) Pageable pageable) {
        Page<ProjectSummaryResponse> projects = projectService.getAllProjects(pageable);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    /**
     * This method retrieves a Project by its ID.
     *
     * @param id the ID of the Project to retrieve
     * @return a response entity with the requested Project and HTTP status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectFullResponse> getProjectById(@PathVariable @Positive Long id) {
        ProjectFullResponse project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    /**
     * This method updates an existing Project.
     *
     * @param id      the ID of the Project to update
     * @param project the request body containing the new details of the Project
     * @return a response entity with a success message and HTTP status code
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProject(
            @PathVariable @Positive Long id, @RequestBody @Valid ProjectFullRequest project
                                               ) {
        projectService.updateProject(project, id);
        return new ResponseEntity<>("Project has been successfully updated", HttpStatus.OK);
    }

    /**
     * This method deletes a Project by its ID.
     *
     * @param id the ID of the Project to delete
     * @return a response entity with a success message and HTTP status code
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable @Positive Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        projectService.deleteProjectById(username, id);
        return new ResponseEntity<>("Project was successfully deleted", HttpStatus.NO_CONTENT);
    }

    /**
     * This method retrieves Projects by category and search query.
     *
     * @param category the category of the Projects to retrieve
     * @param query    the search query
     * @param pageable the pagination information
     * @return a response entity with a list of Projects matching the category and search query and HTTP status code
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ProjectSummaryResponse>> searchProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String query,
            @PageableDefault(size = 10) Pageable pageable
                                                                      ) {
        Page<ProjectSummaryResponse> projects = projectService.getProjectsByCategoryAndSearchQuery(
                category,
                query,
                pageable
                                                                                                  );
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

}