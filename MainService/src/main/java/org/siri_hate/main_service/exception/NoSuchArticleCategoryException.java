package org.siri_hate.main_service.exception;

public class NoSuchArticleCategoryException extends RuntimeException {

  public NoSuchArticleCategoryException(String message) {
    super(message);
  }
}
