package org.siri_hate.user_service.exception;

public class ErrorResponse {

  private final String errorMessage;

  public ErrorResponse(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
