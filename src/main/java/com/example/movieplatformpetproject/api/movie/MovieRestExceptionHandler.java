package com.example.movieplatformpetproject.api.movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class MovieRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<MovieErrorResponse> handleException(MovieNotFoundException exception) {
        return new ResponseEntity<>(new MovieErrorResponse(List.of(exception.getMessage()), HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MovieErrorResponse> handleException(MovieValidationException exception) {
        return new ResponseEntity<>(new MovieErrorResponse(exception.getValidationErrors(), HttpStatus.CONFLICT.value()),
                HttpStatus.CONFLICT);
    }
}
