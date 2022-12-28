package com.vilgodskaia.movieplatformpetproject.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class MovieRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(MovieNotFoundException exception) {
        return new ResponseEntity<>(new CustomErrorResponse(List.of(exception.getMessage())),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(MovieValidationException exception) {
        return new ResponseEntity<>(new CustomErrorResponse(exception.getValidationErrors()),
                HttpStatus.CONFLICT);
    }
}
