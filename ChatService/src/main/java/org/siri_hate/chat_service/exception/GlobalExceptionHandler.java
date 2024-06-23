package org.siri_hate.chat_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is a global exception handler for the application.
 * It handles exceptions and returns an appropriate HTTP response with an error message.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method handles general exceptions.
     * It returns a response with an INTERNAL_SERVER_ERROR status and the exception message.
     *
     * @param e the exception
     * @return a ResponseEntity with an ErrorResponse and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleThrowableException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method handles NoSuchUserException.
     * It returns a response with a NOT_FOUND status and the exception message.
     *
     * @param e the NoSuchUserException
     * @return a ResponseEntity with an ErrorResponse and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleThrowableException(NoSuchUserException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles NoSuchChatException.
     * It returns a response with a NOT_FOUND status and the exception message.
     *
     * @param e the NoSuchChatException
     * @return a ResponseEntity with an ErrorResponse and HTTP status code
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleThrowableException(NoSuchChatException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}