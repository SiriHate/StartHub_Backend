package org.siri_hate.notification_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is responsible for handling all exceptions that occur in the application.
 * It uses the @RestControllerAdvice annotation to apply to all controllers in the application.
 * It uses the @ExceptionHandler annotation to handle specific exceptions.
 * It uses the Lombok @Slf4j annotation for logging.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * This method handles all exceptions of type Exception.
     * It logs the exception message and returns an ErrorResponse with the exception message and a status of INTERNAL_SERVER_ERROR.
     *
     * @param e the exception
     * @return a ResponseEntity with an ErrorResponse and a status of INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleThrowableException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method handles all exceptions of type MailSendException.
     * It logs the exception message and returns an ErrorResponse with the exception message and a status of INTERNAL_SERVER_ERROR.
     *
     * @param e the exception
     * @return a ResponseEntity with an ErrorResponse and a status of INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMailSendException(MailSendException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method handles all exceptions of type UnknownEmailTypeException.
     * It logs the exception message and returns an ErrorResponse with the exception message and a status of INTERNAL_SERVER_ERROR.
     *
     * @param e the exception
     * @return a ResponseEntity with an ErrorResponse and a status of INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUnknownEmailTypeException(UnknownEmailTypeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}