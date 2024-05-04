package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.Project;

import java.util.List;
public interface ProjectService {

    void createProject(Project project);

    List<Project> getAllProjects();

    List<Project> searchProjectsByName(String projectName);

    Project getProjectById(long id);

    void updateProject(Project project);

    void deleteProjectById(long id);

}
