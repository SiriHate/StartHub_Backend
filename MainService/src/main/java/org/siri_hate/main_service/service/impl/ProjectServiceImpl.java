package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import org.siri_hate.main_service.exception.NoSuchProjectFoundException;
import org.siri_hate.main_service.model.dto.mapper.ProjectMapper;
import org.siri_hate.main_service.model.dto.request.project.ProjectFullRequest;
import org.siri_hate.main_service.model.dto.request.project_members.ProjectMemberRequest;
import org.siri_hate.main_service.model.dto.response.project.ProjectFullResponse;
import org.siri_hate.main_service.model.dto.response.project.ProjectSummaryResponse;
import org.siri_hate.main_service.model.entity.Project;
import org.siri_hate.main_service.model.entity.ProjectMember;
import org.siri_hate.main_service.model.entity.User;
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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * This class implements the ProjectService interface.
 * It provides methods for creating, retrieving, updating, and deleting Project entities.
 * It is annotated with @Service, indicating that it's a bean and Spring will create an instance of it at runtime.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    private final ProjectCategoryService projectCategoryService;

    final private UserService userService;

    /**
     * Constructor for ProjectServiceImpl.
     *
     * @param projectRepository      the ProjectRepository to use for database operations.
     * @param projectMapper          the ProjectMapper to use for converting between DTOs and entities.
     * @param projectCategoryService the ProjectCategoryService to use for handling ProjectCategory entities.
     * @param userService            the UserService to use for handling User entities.
     */
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

    /**
     * This method creates a new Project entity from a request DTO and saves it in the database.
     *
     * @param username the username of the User who is creating the Project.
     * @param project  the ProjectFullRequest DTO containing the data for the new Project.
     */
    @Override
    @Transactional
    public void createProject(String username, ProjectFullRequest project) {
        Project projectEntity = projectMapper.toProject(project);
        projectEntity.setUser(userService.findOrCreateUser(username));
        projectEntity.setCategory(projectCategoryService.getProjectCategoryEntityById(project.getCategoryId()));
        projectEntity.setModerationPassed(false);

        Set<ProjectMember> projectMembers = new HashSet<>();
        for (ProjectMemberRequest memberRequest : project.getMembers()) {
            User memberUser = userService.findOrCreateUser(memberRequest.getUsername());
            ProjectMember projectMember = new ProjectMember();
            projectMember.setUser(memberUser);
            projectMember.setRole(memberRequest.getRole());
            projectMembers.add(projectMember);
        }

        projectEntity.setMembers(projectMembers);

        projectEntity.setMembers(projectMembers);

        projectRepository.save(projectEntity);
    }

    /**
     * This method retrieves all Project entities and converts them to summary response DTOs.
     *
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ProjectSummaryResponse DTOs.
     */
    @Override
    public Page<ProjectSummaryResponse> getAllProjects(Pageable pageable) {

        Page<Project> projectsPage = projectRepository.findAll(pageable);

        if (projectsPage.isEmpty()) {
            throw new RuntimeException("No projects found");
        }

        return projectMapper.toProjectSummaryResponsePage(projectsPage);
    }

    /**
     * This method retrieves all Project entities that match a given category and search query, and converts them to summary response DTOs.
     *
     * @param category the category to match.
     * @param query    the search query to match.
     * @param pageable the Pageable to use for pagination.
     * @return a Page of ProjectSummaryResponse DTOs.
     */
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

    /**
     * This method retrieves a Project entity by its ID and converts it to a full response DTO.
     *
     * @param id the ID of the Project to retrieve.
     * @return a ProjectFullResponse DTO.
     */
    @Override
    @Transactional
    public ProjectFullResponse getProjectById(Long id) {

        Optional<Project> project = projectRepository.findById(id);

        if (project.isEmpty()) {
            throw new RuntimeException("No project found with id " + id);
        }

        return projectMapper.toProjectFullResponse(project.get());
    }

    /**
     * This method updates an existing Project entity with data from a request DTO and saves it in the database.
     *
     * @param project the ProjectFullRequest DTO containing the new data for the Project.
     * @param id      the ID of the Project to update.
     */
    @Override
    @Transactional
    public void updateProject(ProjectFullRequest project, Long id) {

        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isEmpty()) {
            throw new RuntimeException("No project with id " + id + " found");
        }

        projectRepository.save(projectMapper.toProject(project));
    }

    /**
     * This method deletes a Project entity by its ID from the database.
     *
     * @param username the username of the User who is deleting the Project.
     * @param id       the ID of the Project to delete.
     */
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