package org.siri_hate.main_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.siri_hate.main_service.model.dto.request.project.ProjectFullRequest;
import org.siri_hate.main_service.model.dto.response.project.ProjectFullResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/main_service/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<String> createProject(@RequestBody @Valid ProjectFullRequest project) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        projectService.createProject(username, project);
        return new ResponseEntity<>("Project has been successfully created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getAllProjects() {
        List<ProjectSummaryResponse> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectFullResponse> getProjectById(@PathVariable @Positive Long id) {
        ProjectFullResponse project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectFullResponse> updateProject(
            @PathVariable @Positive Long id,
            @RequestBody @Valid ProjectFullRequest project
                                                            ) {
        ProjectFullResponse updatedProject = projectService.updateProject(project, id);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable @Positive Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        projectService.deleteProjectById(username, id);
        return new ResponseEntity<>("Project was successfully deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/by-project-name/{projectName}")
    public ResponseEntity<List<ProjectSummaryResponse>> searchProjectsByName(@PathVariable("projectName") String projectName) {
        List<ProjectSummaryResponse> projects = projectService.searchProjectsByName(projectName);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/find-my-projects")
    public ResponseEntity<List<ProjectSummaryResponse>> getAllProjectsByOwnerUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<ProjectSummaryResponse> projects = projectService.searchProjectsByOwnerUsername(username);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

}
