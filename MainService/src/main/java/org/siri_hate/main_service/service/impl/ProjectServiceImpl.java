package org.siri_hate.main_service.service.impl;

import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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
import org.siri_hate.main_service.service.ProjectSubscriberService;
import org.siri_hate.main_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final ProjectMapper projectMapper;
  private final ProjectCategoryService projectCategoryService;
  private final UserService userService;
  private final ProjectSubscriberService projectSubscriberService;

  @Autowired
  public ProjectServiceImpl(
      ProjectRepository projectRepository,
      ProjectMapper projectMapper,
      ProjectCategoryService projectCategoryService,
      @Lazy UserService userService,
      ProjectSubscriberService projectSubscriberService) {
    this.projectRepository = projectRepository;
    this.projectMapper = projectMapper;
    this.projectCategoryService = projectCategoryService;
    this.userService = userService;
    this.projectSubscriberService = projectSubscriberService;
  }

  @Override
  @Transactional
  public void createProject(String username, ProjectFullRequest project) {
    Project projectEntity = projectMapper.toProject(project);
    projectEntity.setUser(userService.findOrCreateUser(username));
    projectEntity.setCategory(
        projectCategoryService.getProjectCategoryEntityById(project.getCategory().getId()));
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

  @Override
  public Page<ProjectSummaryResponse> getProjectsByCategoryAndSearchQuery(
      String category, String query, Pageable pageable) {
    Specification<Project> spec =
        Specification.where(ProjectSpecification.projectNameStartsWith(query))
            .and(ProjectSpecification.hasCategory(category));
    Page<Project> projects = projectRepository.findAll(spec, pageable);
    if (projects.isEmpty()) {
      throw new NoSuchProjectFoundException("No projects found");
    }
    return projectMapper.toProjectSummaryResponsePage(projects);
  }

  @Override
  @Transactional
  public ProjectFullResponse getProjectInfoById(Long id) {
    Optional<Project> project = projectRepository.findById(id);
    if (project.isEmpty()) {
      throw new RuntimeException("No project found with id " + id);
    }
    Project projectEntity = project.get();
    return projectMapper.toProjectFullResponse(projectEntity);
  }

  @Override
  public Project getProjectById(Long id) {
    Optional<Project> project = projectRepository.findById(id);
    if (project.isEmpty()) {
      throw new RuntimeException("No project found with id " + id);
    }
    return project.get();
  }

  @Override
  @Transactional
  public void updateProject(ProjectFullRequest request, Long id) {
    Project existingProject =
        projectRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("No project with id " + id + " found"));

    // Обновляем базовые поля проекта
    existingProject.setProjectName(request.getProjectName());
    existingProject.setProjectDescription(request.getProjectDescription());
    existingProject.setProjectLogoUrl(request.getProjectLogoUrl());
    existingProject.setCategory(
        projectCategoryService.getProjectCategoryEntityById(request.getCategory().getId()));

    // Очищаем существующих участников
    existingProject.getMembers().clear();
    
    // Добавляем новых участников
    for (ProjectMemberRequest memberRequest : request.getMembers()) {
      User memberUser = userService.findOrCreateUser(memberRequest.getUsername());
      ProjectMember projectMember = new ProjectMember();
      projectMember.setUser(memberUser);
      projectMember.setProject(existingProject);
      projectMember.setRole(memberRequest.getRole());
      existingProject.getMembers().add(projectMember);
    }
    
    projectRepository.save(existingProject);
    projectSubscriberService.notifySubscribersAboutUpdate(id, existingProject.getProjectName());
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

  @Override
  @Transactional
  public boolean toggleProjectLike(String username, Long projectId) {
    Project project =
        projectRepository
            .findById(projectId)
            .orElseThrow(
                () ->
                    new NoSuchProjectFoundException("No project with id " + projectId + " found"));

    User user = userService.findOrCreateUser(username);

    boolean alreadyLiked =
        project.getProjectLikes().stream()
            .anyMatch(like -> like.getUser().getId().equals(user.getId()));

    if (alreadyLiked) {
      project.getProjectLikes().removeIf(like -> like.getUser().getId().equals(user.getId()));
      projectRepository.save(project);
      return false;
    } else {
      project.addLike(user);
      projectRepository.save(project);
      return true;
    }
  }

  @Override
  public Long getProjectLikesCount(Long projectId) {
    Project project =
        projectRepository
            .findById(projectId)
            .orElseThrow(
                () ->
                    new NoSuchProjectFoundException("No project with id " + projectId + " found"));
    return (long) project.getProjectLikes().size();
  }

  @Override
  public Page<ProjectSummaryResponse> getModeratedProjects(
      String category, String query, Pageable pageable) {
    Specification<Project> spec =
        Specification.where(ProjectSpecification.projectNameStartsWith(query))
            .and(ProjectSpecification.hasCategory(category))
            .and(
                (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.isTrue(root.get("moderationPassed")));
    Page<Project> projects = projectRepository.findAll(spec, pageable);
    if (projects.isEmpty()) {
      throw new NoSuchProjectFoundException("No moderated projects found");
    }
    return projectMapper.toProjectSummaryResponsePage(projects);
  }

  @Override
  public Page<ProjectSummaryResponse> getUnmoderatedProjects(
      String category, String query, Pageable pageable) {
    Specification<Project> spec =
        Specification.where(ProjectSpecification.projectNameStartsWith(query))
            .and(ProjectSpecification.hasCategory(category))
            .and(
                (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.isFalse(root.get("moderationPassed")));
    Page<Project> projects = projectRepository.findAll(spec, pageable);
    if (projects.isEmpty()) {
      throw new NoSuchProjectFoundException("No unmoderated projects found");
    }
    return projectMapper.toProjectSummaryResponsePage(projects);
  }

  @Override
  @Transactional
  public void updateProjectModerationStatus(Long projectId, Boolean moderationPassed) {
    Project project =
        projectRepository
            .findById(projectId)
            .orElseThrow(
                () ->
                    new NoSuchProjectFoundException("No project with id " + projectId + " found"));
    project.setModerationPassed(moderationPassed);
    projectRepository.save(project);
  }

  @Override
  @Transactional
  public Page<ProjectSummaryResponse> getProjectsByOwner(String username, Pageable pageable) {
    Page<Project> projects = projectRepository.findByUserUsername(username, pageable);
    if (projects.isEmpty()) {
      throw new NoSuchProjectFoundException("No projects found for user: " + username);
    }
    return projectMapper.toProjectSummaryResponsePage(projects);
  }

  @Override
  @Transactional
  public Page<ProjectSummaryResponse> getProjectsByMember(String username, Pageable pageable) {
    Page<Project> projects = projectRepository.findByMembersUserUsername(username, pageable);
    if (projects.isEmpty()) {
      throw new NoSuchProjectFoundException("No projects found for user: " + username);
    }
    return projectMapper.toProjectSummaryResponsePage(projects);
  }
}
