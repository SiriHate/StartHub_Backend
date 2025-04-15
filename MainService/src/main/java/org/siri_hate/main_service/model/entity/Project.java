package org.siri_hate.main_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.siri_hate.main_service.model.entity.category.ProjectCategory;
import org.siri_hate.main_service.model.entity.survey.ProjectSurvey;

@Entity
@Table(name = "projects")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToMany(
      fetch = FetchType.EAGER,
      cascade = {CascadeType.ALL})
  @JoinTable(
      name = "project_membership",
      joinColumns = @JoinColumn(name = "project_id"),
      inverseJoinColumns = @JoinColumn(name = "member_id"))
  private Set<ProjectMember> members = new HashSet<>();

  @Column(name = "project_name")
  private String projectName;

  @Column(name = "project_description")
  private String projectDescription;

  @Column(name = "project_logo_url")
  private String projectLogoUrl;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private ProjectCategory category;

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProjectLike> projectLikes = new ArrayList<>();

  @Column(name = "moderation_passed")
  private Boolean moderationPassed;

  @JsonIgnore
  @OneToOne(
      mappedBy = "project",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private ProjectSurvey survey;

  public Project() {}

  public Project(
      User user,
      Set<ProjectMember> members,
      String projectName,
      String projectDescription,
      String projectLogoUrl,
      ProjectCategory category,
      List<ProjectLike> projectLikes,
      Boolean moderationPassed) {
    this.user = user;
    this.members = members;
    this.projectName = projectName;
    this.projectDescription = projectDescription;
    this.projectLogoUrl = projectLogoUrl;
    this.category = category;
    this.projectLikes = projectLikes;
    this.moderationPassed = moderationPassed;
  }

  public Project(
      Set<ProjectMember> members,
      String projectName,
      String projectDescription,
      String projectLogoUrl,
      ProjectCategory category,
      List<ProjectLike> projectLikes,
      Boolean moderationPassed) {
    this.members = members;
    this.projectName = projectName;
    this.projectDescription = projectDescription;
    this.projectLogoUrl = projectLogoUrl;
    this.category = category;
    this.projectLikes = projectLikes;
    this.moderationPassed = moderationPassed;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<ProjectMember> getMembers() {
    return members;
  }

  public void setMembers(Set<ProjectMember> members) {
    this.members = members;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getProjectDescription() {
    return projectDescription;
  }

  public void setProjectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
  }

  public String getProjectLogoUrl() {
    return projectLogoUrl;
  }

  public void setProjectLogoUrl(String projectLogoUrl) {
    this.projectLogoUrl = projectLogoUrl;
  }

  public ProjectCategory getCategory() {
    return category;
  }

  public void setCategory(ProjectCategory category) {
    this.category = category;
  }

  public List<ProjectLike> getProjectLikes() {
    return projectLikes;
  }

  public void setProjectLikes(List<ProjectLike> projectLikes) {
    this.projectLikes = projectLikes;
  }

  public void addLike(User user) {

    boolean alreadyLiked =
        projectLikes.stream().anyMatch(like -> like.getUser().getId().equals(user.getId()));

    if (alreadyLiked) {
      return;
    }

    ProjectLike like = new ProjectLike(user, this);
    projectLikes.add(like);
  }

  public Boolean getModerationPassed() {
    return moderationPassed;
  }

  public void setModerationPassed(Boolean moderationPassed) {
    this.moderationPassed = moderationPassed;
  }

  public ProjectSurvey getSurvey() {
    return survey;
  }

  public void setSurvey(ProjectSurvey survey) {
    this.survey = survey;
  }
}
