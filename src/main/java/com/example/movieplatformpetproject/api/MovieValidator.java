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

    public static final String FIELD_NULL_POSTFIX = " field should not be null";
    public static final String FIELD_EMPTY_POSTFIX = " field should not be empty";
    public static final String YEAR_NOT_VALID = "The movie release year should be not before 1895";
    public static final String DURATION_NOT_VALID = "The movie duration should not be less than 1 minute";
    public static final String MOVIE_NOT_UNIQUE = "A movie with this title, release year, and director already exists";

    private final MovieRepository movieRepository;


    public void validate(Movie movie) {
        List<String> validationErrors = new ArrayList<>();

        //Validate Title
        checkNotEmptyOrNull("Title", movie.getTitle(), validationErrors);

        //Validate Year
        checkNotNull("Year", movie.getYear(), validationErrors);
        if (movie.getYear() != null && movie.getYear() < 1895) {
            validationErrors.add(YEAR_NOT_VALID);
        }

        //Validate Genre
        checkNotNull("Genre", movie.getGenre(), validationErrors);

        //Validate Duration
        checkNotNull("Duration", movie.getDuration(), validationErrors);
        if (movie.getDuration() != null && movie.getDuration() <= 0) {
            validationErrors.add(DURATION_NOT_VALID);
        }

        //Validate Director
        checkNotEmptyOrNull("Director", movie.getDirector(), validationErrors);

        //Check if this movie already exists in DB
        if (movie.getTitle() != null && movie.getYear() != null && movie.getDirector() != null) {
            Optional<Movie> existingMovie = movieRepository.findByTitleAndYearAndDirector(movie.getTitle(), movie.getYear(), movie.getDirector());
            if (existingMovie.isPresent() && !existingMovie.get().getId().equals(movie.getId())) {
                validationErrors.add(MOVIE_NOT_UNIQUE);
            }
        }

        //In case there is at least one invalid field throw a validation exception
        if (!validationErrors.isEmpty()) {
            throw new MovieValidationException(validationErrors);
        }
    }

    private void checkNotNull(String fieldName, Object value, List<String> validationErrors) {
        if (value == null) {
            validationErrors.add(fieldName + FIELD_NULL_POSTFIX);
        }
    }

    private void checkNotEmptyOrNull(String fieldName, String value, List<String> validationErrors) {
        checkNotNull(fieldName, value, validationErrors);
        if (value != null && value.isEmpty()) {
            validationErrors.add(fieldName + FIELD_EMPTY_POSTFIX);
        }
    }
}
