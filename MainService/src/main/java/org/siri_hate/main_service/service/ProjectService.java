package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.Project;

import java.util.List;
public interface ProjectService {

    void createProject(String username, Project project);

    List<Project> getAllProjects();

    List<Project> searchProjectsByName(String projectName);

    List<Project> searchProjectsByOwnerUsername(String username);

    Project getProjectById(Long id);

    void updateProject(Project project);

    void deleteProjectById(Long id);

}
