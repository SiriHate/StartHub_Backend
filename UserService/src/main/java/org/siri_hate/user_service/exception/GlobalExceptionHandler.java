package org.siri_hate.user_service.exception;

import org.siri_hate.user_service.aspect.LoggingAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Handles general exceptions.
     *
     * @param e the exception
     * @return a response entity with an error response and a status of INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleThrowableException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles BadCredentialsException.
     *
     * @param e the exception
     * @return a response entity with an error response and a status of CONFLICT
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles NoSuchUserException.
     *
     * @param e the exception
     * @return a response entity with an error response and a status of NOT_FOUND
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNoSuchUserException(NoSuchUserException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles BadRequestBodyException.
     *
     * @param e the exception
     * @return a response entity with an error response and a status of BAD_REQUEST
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBadRequestBodyException(BadRequestBodyException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles ValidationException.
     *
     * @param e the exception
     * @return a response entity with an error response and a status of BAD_REQUEST
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles AuthenticationException.
     *
     * @param e the exception
     * @return a response entity with an error response and a status of UNAUTHORIZED
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles NoSuchConfirmationTokenException.
     *
     * @param e the exception
     * @return a response entity with an error response and a status of UNAUTHORIZED
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNoSuchConfirmationTokenException(NoSuchConfirmationTokenException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles MismatchedPasswordException.
     *
     * @param e the exception
     * @return a response entity with an error response and a status of UNAUTHORIZED
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMismatchedPasswordException(MismatchedPasswordException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles BadCredentialsException.
     *
     * @param e the exception
     * @return a response entity with an error response and a status of UNAUTHORIZED
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleMismatchedPasswordException(BadCredentialsException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

}
