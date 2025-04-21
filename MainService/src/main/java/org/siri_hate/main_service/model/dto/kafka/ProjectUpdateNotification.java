package org.siri_hate.main_service.model.dto.kafka;

import java.time.LocalDateTime;

public class ProjectUpdateNotification {

  private String projectName;
  private String updateDate;
  private String projectLink;
  private String username;

  public ProjectUpdateNotification() {}

  public ProjectUpdateNotification(
      String projectName, String updateDate, String projectLink, String username) {
    this.projectName = projectName;
    this.updateDate = updateDate;
    this.projectLink = projectLink;
    this.username = username;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }

  public String getProjectLink() {
    return projectLink;
  }

  public void setProjectLink(String projectLink) {
    this.projectLink = projectLink;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
