package org.siri_hate.main_service.model.dto.kafka;

public class UserDeletionMessage {

  private String username;

  public UserDeletionMessage() {}

  public UserDeletionMessage(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
