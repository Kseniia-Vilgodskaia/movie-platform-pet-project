package com.example.movieplatformpetproject.api.streamingplatform;

import com.example.movieplatformpetproject.api.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class StreamingPlatformRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(StreamingPlatformNotFoundException exception) {
        return new ResponseEntity<>(new CustomErrorResponse(List.of(exception.getMessage())), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(StreamingPlatformValidationException exception) {
        return new ResponseEntity<>(new CustomErrorResponse(exception.getErrorMessages()), HttpStatus.CONFLICT);
    }
}
