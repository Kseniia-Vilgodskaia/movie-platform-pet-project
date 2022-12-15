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
        checkNotEmptyOrNull("Title", movie.getTitle(), validationErrors);

        //Validate Year
        checkNotNull("Year", movie.getYear(), validationErrors);
        if (movie.getYear() != null && movie.getYear() < 1895) {
            validationErrors.add("The movie release year should be not before 1895");
        }

        //Validate Genre
        checkNotNull("Genre", movie.getGenre(), validationErrors);

        //Validate Duration
        checkNotNull("Duration", movie.getDuration(), validationErrors);
        if (movie.getDuration() != null && movie.getDuration() <= 0) {
            validationErrors.add("The movie duration should not be less than 1 minute");
        }

        //Validate Director
        checkNotEmptyOrNull("Director", movie.getDirector(), validationErrors);

        //In case there is at least one invalid field throw a validation exception
        if (!validationErrors.isEmpty()) {
            throw new MovieValidationException(validationErrors);
        }
    }

    private void checkNotNull(String fieldName, Object value, List<String> validationErrors) {
        if (value == null) {
            validationErrors.add(fieldName + " field should not be null");
        }
    }

    private void checkNotEmptyOrNull(String fieldName, String value, List<String> validationErrors) {
        checkNotNull(fieldName, value, validationErrors);
        if (value != null && value.isEmpty()) {
            validationErrors.add(fieldName + " field should not be empty");
        }
    }
}
