package org.siri_hate.main_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.siri_hate.main_service.model.entity.Project;
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
@RequestMapping("/api/v1/main/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<String> createProject(@RequestBody @Valid Project project) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        projectService.createProject(username, project);
        return new ResponseEntity<>("Project has been successfully created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projectList = projectService.getAllProjects();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable @Positive Long id) {
        Project project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Project> updateProject(@RequestBody @Valid Project project) {
        projectService.updateProject(project);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable @Positive Long id) {
        projectService.deleteProjectById(id);
        return new ResponseEntity<>("Project was successfully deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/by-project-name/{projectName}")
    public ResponseEntity<List<Project>> searchProjectsByName(@PathVariable("projectName") String projectName) {
        projectService.searchProjectsByName(projectName);
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/find-my-projects")
    public ResponseEntity<List<Project>> getAllProjectsByOwnerUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Project> projects = projectService.searchProjectsByOwnerUsername(username);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

}
