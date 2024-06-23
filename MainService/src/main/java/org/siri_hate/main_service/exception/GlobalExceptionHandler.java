package org.siri_hate.main_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * This class is a global exception handler for the application.
 * It provides methods to handle different types of exceptions and return appropriate HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method handles general exceptions.
     *
     * @param e the exception
     * @return a response entity with an error message and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleThrowableException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method handles exceptions related to non-existent Article categories.
     *
     * @param e the exception
     * @return a response entity with an error message and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNoSuchArticleCategoryException(NoSuchArticleCategoryException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles exceptions related to non-existent Articles.
     *
     * @param e the exception
     * @return a response entity with an error message and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNoSuchArticleFoundException(NoSuchArticleFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles exceptions related to non-existent News categories.
     *
     * @param e the exception
     * @return a response entity with an error message and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNoSuchNewsCategoryException(NoSuchNewsCategoryException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles exceptions related to non-existent News.
     *
     * @param e the exception
     * @return a response entity with an error message and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNoSuchNewsFoundException(NoSuchNewsFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles exceptions related to non-existent Project categories.
     *
     * @param e the exception
     * @return a response entity with an error message and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNoSuchProjectCategoryException(NoSuchProjectCategoryException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles exceptions related to non-existent Projects.
     *
     * @param e the exception
     * @return a response entity with an error message and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNoSuchProjectFoundException(NoSuchProjectFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles exceptions related to non-existent Users.
     *
     * @param e the exception
     * @return a response entity with an error message and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNoSuchUserException(NoSuchUserException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles exceptions related to non-existent Users.
     *
     * @param e the exception
     * @return a response entity with an error message and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNoSuchUserFoundException(NoSuchUserException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}