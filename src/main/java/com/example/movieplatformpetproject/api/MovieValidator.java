package com.example.movieplatformpetproject.api;

import com.example.movieplatformpetproject.model.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieValidator {


    public void validate(Movie movie) {
        List<String> validationErrors = new ArrayList<>();

        //Validate Title
        checkIfEmptyOrNull("Title", movie.getTitle(), validationErrors);
        if (movie.getTitle() != null && !movie.getTitle().matches("[a-zA-Z0-9]")) {
            validationErrors.add("The movie title should consist of letters of the Latin alphabet or/and numbers");
        }

        //Validate Year
        checkIfEmptyOrNull("Year", movie.getYear(), validationErrors);
        if (movie.getYear() != null && movie.getYear() < 1895) {
            validationErrors.add("The movie release year should be not before 1895");
        }

        //Validate Genre
        checkIfEmptyOrNull("Genre", movie.getGenre(), validationErrors);

        //Validate Duration
        checkIfEmptyOrNull("Duration", movie.getDuration(), validationErrors);
        if (movie.getDuration() != null && movie.getDuration() <= 0) {
            validationErrors.add("The movie duration should not be less than 1 minute");
        }

        //Validate Director
        checkIfEmptyOrNull("Director", movie.getDirector(), validationErrors);
        if (movie.getDirector() != null && !movie.getDirector().matches("[a-zA-Z]")) {
            validationErrors.add("The director's name should consist of letters of the Latin alphabet");
        }

        //In case there is at least one invalid field throw a validation exception
        if (!validationErrors.isEmpty()) {
            throw new MovieValidationException(validationErrors);
        }
    }

    private void checkIfEmptyOrNull(String fieldName, Object value, List<String> validationErrors) {
        if (value == null) {
            validationErrors.add(fieldName + " field should not be null");
        } else if (value instanceof String && ((String) value).isEmpty()) {
            validationErrors.add(fieldName + " field should not be empty");
        }
    }
}
