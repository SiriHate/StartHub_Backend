package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.exception.NoSuchProjectFoundException;
import org.siri_hate.main_service.model.dto.mapper.ProjectMapper;
import org.siri_hate.main_service.model.dto.request.project.ProjectFullRequest;
import org.siri_hate.main_service.model.dto.response.project.ProjectFullResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.model.entity.Project;
import org.siri_hate.main_service.repository.ProjectRepository;
import org.siri_hate.main_service.repository.adapters.ProjectSpecification;
import org.siri_hate.main_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    public Page<ProjectSummaryResponse> getAllProjects(Pageable pageable) {

        Page<Project> projectsPage = projectRepository.findAll(pageable);

        if (projectsPage.isEmpty()) {
            throw new RuntimeException("No projects found");
        }

        return projectMapper.toProjectSummaryResponsePage(projectsPage);
    }

    public Page<ProjectSummaryResponse> getProjectsByCategoryAndSearchQuery(
            String category,
            String query,
            Pageable pageable
                                                                           ) {
        Specification<Project> spec = Specification.where(ProjectSpecification.projectNameStartsWith(query))
                .and(ProjectSpecification.hasCategory(category));

        Page<Project> projects = projectRepository.findAll(spec, pageable);

        if (projects.isEmpty()) {
            throw new NoSuchProjectFoundException("No projects found");
        }

        return projectMapper.toProjectSummaryResponsePage(projects);
    }

    @Override
    public Page<ProjectSummaryResponse> searchProjectsByOwnerUsername(String username, Pageable pageable) {

        Page<Project> projectsPage = projectRepository.findProjectsByProjectOwner(username, pageable);

        if (projectsPage.isEmpty()) {
            throw new RuntimeException("No projects have been found for user with" + username + " username");
        }

        return projectMapper.toProjectSummaryResponsePage(projectsPage);
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
