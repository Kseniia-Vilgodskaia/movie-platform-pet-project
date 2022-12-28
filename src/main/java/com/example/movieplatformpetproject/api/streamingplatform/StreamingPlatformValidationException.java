package com.example.movieplatformpetproject.api.streamingplatform;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class StreamingPlatformValidationException extends RuntimeException {
    private final List<String> errorMessages;
}
