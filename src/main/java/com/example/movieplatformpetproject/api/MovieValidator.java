package com.example.movieplatformpetproject.api;

import com.example.movieplatformpetproject.model.Movie;
import com.example.movieplatformpetproject.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieValidator {

    private final MovieRepository movieRepository;


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

        //Check if this movie already exists in DB
        if (movie.getTitle() != null && movie.getYear() != null && movie.getDirector() != null) {
            Optional<Movie> existingMovie = movieRepository.findByTitleAndYearAndDirector(movie.getTitle(), movie.getYear(), movie.getDirector());
            if (existingMovie.isPresent() && !existingMovie.get().getId().equals(movie.getId())) {
                validationErrors.add("A movie with this title, release year, and director already exists");
            }
        }

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
