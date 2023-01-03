package com.vilgodskaia.movieplatformpetproject.config.exceptions;

import java.util.UUID;

public class MovieOnStreamingPlatformNotFoundException extends RuntimeException {

    public MovieOnStreamingPlatformNotFoundException(UUID id) {
        super("Relation between a movie and a streaming platform not found by ID: " + id);
    }
}
