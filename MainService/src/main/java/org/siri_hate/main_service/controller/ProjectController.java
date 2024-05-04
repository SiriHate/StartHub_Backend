package org.siri_hate.main_service.controller;

import org.siri_hate.main_service.model.Project;
import org.siri_hate.main_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/main/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<String> createProject(@RequestBody Project project) {
        projectService.createProject(project);
        return new ResponseEntity<>("Project has been successfully created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projectList = projectService.getAllProjects();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Project> getProjectById(@RequestParam long id) {
        Project project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        projectService.updateProject(project);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProjectById(@RequestParam long id) {
        projectService.deleteProjectById(id);
        return new ResponseEntity<>("Project was successfully deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjectsByName(@RequestParam("projectName") String projectName) {
        projectService.searchProjectsByName(projectName);
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

}
