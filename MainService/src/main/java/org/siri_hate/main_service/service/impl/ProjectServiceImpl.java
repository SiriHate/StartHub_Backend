package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.model.dto.mapper.ProjectMapper;
import org.siri_hate.main_service.model.dto.request.project.ProjectFullRequest;
import org.siri_hate.main_service.model.dto.response.project.ProjectFullResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.model.entity.Project;
import org.siri_hate.main_service.repository.ProjectRepository;
import org.siri_hate.main_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    @Transactional
    public void createProject(String username, ProjectFullRequest project) {
        Project projectEntity = projectMapper.toProject(project);
        projectEntity.setProjectOwner(username);
        projectRepository.save(projectEntity);
    }

    @Override
    public List<ProjectSummaryResponse> getAllProjects() {

        List<Project> projectList = projectRepository.findAll();

        if (projectList.isEmpty()) {
            throw new RuntimeException("No projects found");
        }

        return projectMapper.toProjectSummaryResponseList(projectList);
    }

    @Override
    public List<ProjectSummaryResponse> searchProjectsByName(String projectName) {

        List<Project> projectList = projectRepository.findProjectsByProjectNameContainingIgnoreCase(projectName);

        if (projectList.isEmpty()) {
            throw new RuntimeException("No projects found");
        }

        return projectMapper.toProjectSummaryResponseList(projectList);
    }

    @Override
    public List<ProjectSummaryResponse> searchProjectsByOwnerUsername(String username) {

        List<Project> projectList = projectRepository.findProjectsByProjectOwner(username);

        if (projectList.isEmpty()) {
            throw new RuntimeException("No projects have been found for user with" + username + " username");
        }

        return projectMapper.toProjectSummaryResponseList(projectList);
    }

    @Override
    @Transactional
    public ProjectFullResponse getProjectById(Long id) {

        Optional<Project> project = projectRepository.findById(id);

        if (project.isEmpty()) {
            throw new RuntimeException("No project found with id " + id);
        }

        return projectMapper.toProjectFullResponse(project.get());
    }

    @Override
    @Transactional
    public ProjectFullResponse updateProject(ProjectFullRequest project, Long id) {
//
//        Optional<Project> projectOptional = projectRepository.findById(id);
//
//        if (projectOptional.isEmpty()) {
//            throw new RuntimeException("No project with id " + id + " found");
//        }
//
//        projectRepository.save(projectMapper.toProject(project));
//        return projectMapper.toProjectFullResponse(project);
        return null;
    }

    @Override
    @Transactional
    public void deleteProjectById(String username, Long id) {

        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isEmpty()) {
            throw new RuntimeException("No project with id " + id + " found");
        }

        if (!(projectOptional.get().getProjectOwner().equals(username))) {
            throw new RuntimeException("User is not owner of project with id " + id);
        }

        projectRepository.delete(projectOptional.get());
    }

}
