package com.example.movieplatformpetproject.api.movie;

import java.util.UUID;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(UUID id) {
        super("Movie not found by ID: " + id);
    }
}
