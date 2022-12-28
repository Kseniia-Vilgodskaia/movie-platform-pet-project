package com.example.movieplatformpetproject.config.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class StreamingPlatformValidationException extends RuntimeException {
    private final List<String> errorMessages;
}
