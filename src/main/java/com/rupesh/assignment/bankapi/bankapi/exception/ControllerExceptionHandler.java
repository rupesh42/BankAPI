package com.rupesh.assignment.bankapi.bankapi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * This class is implemented for Error handling.
 * 
 * @author Rupesh
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(BankAPIException.class)
  public ResponseEntity<ErrorMessage> resourceNotFoundException(BankAPIException ex,
      WebRequest request) {
    ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(),
        ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),
        ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
