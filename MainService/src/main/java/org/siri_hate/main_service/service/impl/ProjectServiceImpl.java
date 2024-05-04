package org.siri_hate.main_service.service.impl;

import org.siri_hate.main_service.model.Project;
import org.siri_hate.main_service.repository.ProjectRepository;
import org.siri_hate.main_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void createProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {

        List<Project> projectList = projectRepository.findAll();

        if (projectList.isEmpty()) {
            throw new RuntimeException("No projects found");
        }

        return projectList;
    }

    @Override
    public List<Project> searchProjectsByName(String projectName) {
        List<Project> projectList = projectRepository.findProjectsByProjectNameContainingIgnoreCase(projectName);

        if (projectList.isEmpty()) {
            throw new RuntimeException("No projects found");
        }

        return projectList;
    }

    @Override
    public Project getProjectById(long id) {

        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isEmpty()) {
            throw new RuntimeException("No project with id " + id + " found");
        }

        return projectOptional.get();
    }

    @Override
    public void updateProject(Project project) {

        Optional<Project> projectOptional = projectRepository.findById(project.getId());

        if (projectOptional.isEmpty()) {
            throw new RuntimeException("No project with id " + project.getId() + " found");
        }

        projectRepository.save(project);
    }

    @Override
    public void deleteProjectById(long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isEmpty()) {
            throw new RuntimeException("No project with id " + id + " found");
        }

        projectRepository.delete(projectOptional.get());
    }

}