package org.siri_hate.user_service.exception;

public class BadRequestBodyException extends RuntimeException {

  public BadRequestBodyException(String message) {
    super(message);
  }
}
