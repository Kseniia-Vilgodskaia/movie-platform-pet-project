package com.vilgodskaia.movieplatformpetproject.config.exceptions;

import java.util.UUID;

public class StreamingPlatformNotFoundException extends RuntimeException {

    public StreamingPlatformNotFoundException(UUID id) {
        super("Streaming Platform not found by ID: " + id);
    }
}
