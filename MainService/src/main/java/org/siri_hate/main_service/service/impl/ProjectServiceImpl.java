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
import org.siri_hate.main_service.service.ProjectCategoryService;
import org.siri_hate.main_service.service.ProjectService;
import org.siri_hate.main_service.service.UserService;
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

    private final ProjectCategoryService projectCategoryService;

    final private UserService userService;

    @Autowired
    public ProjectServiceImpl(
            ProjectRepository projectRepository,
            ProjectMapper projectMapper,
            ProjectCategoryService projectCategoryService,
            UserService userService
                             ) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.projectCategoryService = projectCategoryService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void createProject(String username, ProjectFullRequest project) {
        Project projectEntity = projectMapper.toProject(project);
        projectEntity.setUser(userService.findOrCreateUser(username));
        projectEntity.setCategory(projectCategoryService.getProjectCategoryEntityById(project.getCategoryId()));
        projectEntity.setModerationPassed(false);
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
    public void updateProject(ProjectFullRequest project, Long id) {

        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isEmpty()) {
            throw new RuntimeException("No project with id " + id + " found");
        }

        projectRepository.save(projectMapper.toProject(project));
    }

    @Override
    @Transactional
    public void deleteProjectById(String username, Long id) {

        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isEmpty()) {
            throw new RuntimeException("No project with id " + id + " found");
        }

        if (!(projectOptional.get().getUser().getUsername().equals(username))) {
            throw new RuntimeException("User is not owner of project with id " + id);
        }

        projectRepository.delete(projectOptional.get());
    }

}
