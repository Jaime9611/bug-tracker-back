package com.practice.bugtracker.validations;

import com.practice.bugtracker.validations.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {

    ErrorResponse body = ErrorResponse.builder()
        .status(HttpStatus.NOT_FOUND.value())
        .error(exception.getMessage())
        .build();

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }
}
