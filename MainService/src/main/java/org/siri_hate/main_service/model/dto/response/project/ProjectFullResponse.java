package org.siri_hate.main_service.model.dto.response.project;

import java.util.Set;
import org.siri_hate.main_service.model.dto.response.project_members.ProjectMembersSummaryResponse;
import org.siri_hate.main_service.model.dto.response.user.UserFullResponse;

public class ProjectFullResponse {

  private Long id;
  private UserFullResponse projectOwner;
  private Set<ProjectMembersSummaryResponse> members;
  private String projectName;
  private String projectDescription;
  private String projectLogoUrl;
  private String category;
  private String stage;
  private Long likes;

  public ProjectFullResponse() {}

  public ProjectFullResponse(
      Long id,
      UserFullResponse projectOwner,
      Set<ProjectMembersSummaryResponse> members,
      String projectName,
      String projectDescription,
      String projectLogoUrl,
      String category,
      String stage,
      Long likes) {
    this.id = id;
    this.projectOwner = projectOwner;
    this.members = members;
    this.projectName = projectName;
    this.projectDescription = projectDescription;
    this.projectLogoUrl = projectLogoUrl;
    this.category = category;
    this.stage = stage;
    this.likes = likes;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserFullResponse getProjectOwner() {
    return projectOwner;
  }

  public void setProjectOwner(UserFullResponse projectOwner) {
    this.projectOwner = projectOwner;
  }

  public Set<ProjectMembersSummaryResponse> getMembers() {
    return members;
  }

  public void setMembers(Set<ProjectMembersSummaryResponse> members) {
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public Long getLikes() {
    return likes;
  }

  public void setLikes(Long likes) {
    this.likes = likes;
  }
}
