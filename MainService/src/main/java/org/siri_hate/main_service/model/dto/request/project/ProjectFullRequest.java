package org.siri_hate.main_service.model.dto.request.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import org.siri_hate.main_service.model.dto.request.project_members.ProjectMemberRequest;

public class ProjectFullRequest {

  private Set<ProjectMemberRequest> members = new HashSet<>();

  @NotBlank(message = "Project name should not be blank")
  private String projectName;

  @NotBlank(message = "Project description should not be blank")
  private String projectDescription;

  private String projectLogoUrl;

  @Positive(message = "Category id should be positive")
  private Long categoryId;

  @NotBlank(message = "Stage should not be blank")
  private String stage;

  public ProjectFullRequest() {}

  public ProjectFullRequest(
      Set<ProjectMemberRequest> members,
      String projectName,
      String projectDescription,
      String projectLogoUrl,
      Long categoryId,
      String stage) {
    this.members = members;
    this.projectName = projectName;
    this.projectDescription = projectDescription;
    this.projectLogoUrl = projectLogoUrl;
    this.categoryId = categoryId;
    this.stage = stage;
  }

  public Set<ProjectMemberRequest> getMembers() {
    return members;
  }

  public void setMembers(Set<ProjectMemberRequest> members) {
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

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }
}
