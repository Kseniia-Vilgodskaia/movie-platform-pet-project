package com.vilgodskaia.movieplatform.core.service;

import com.vilgodskaia.movieplatform.core.config.exceptions.ValidationException;
import com.vilgodskaia.movieplatform.core.model.Movie;
import com.vilgodskaia.movieplatform.core.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.vilgodskaia.movieplatform.core.util.ValidationUtil.checkNotEmptyAndNotNull;
import static com.vilgodskaia.movieplatform.core.util.ValidationUtil.checkNotNull;

@Component
@RequiredArgsConstructor
public class MovieValidator {

    public static final String YEAR_NOT_VALID = "The movie release year should be not before 1895";
    public static final String DURATION_NOT_VALID = "The movie duration should not be less than 1 minute";
    public static final String MOVIE_NOT_UNIQUE = "A movie with this title, release year, and director already exists";

    private final MovieRepository movieRepository;


    public void validate(Movie movie) {
        List<String> validationErrors = new ArrayList<>();

        //Validate Title
        validationErrors.addAll(checkNotEmptyAndNotNull("Title", movie.getTitle()));

        //Validate Year
        validationErrors.addAll(checkNotNull("Year", movie.getYear()));
        if (movie.getYear() != null && movie.getYear() < 1895) {
            validationErrors.add(YEAR_NOT_VALID);
        }

        //Validate Genre
        validationErrors.addAll(checkNotNull("Genre", movie.getGenre()));

        //Validate Duration
        validationErrors.addAll(checkNotNull("Duration", movie.getDuration()));
        if (movie.getDuration() != null && movie.getDuration() <= 0) {
            validationErrors.add(DURATION_NOT_VALID);
        }

        //Validate Director
        validationErrors.addAll(checkNotEmptyAndNotNull("Director", movie.getDirector()));

        //Check if this movie already exists in DB
        if (movie.getTitle() != null && movie.getYear() != null && movie.getDirector() != null) {
            Optional<Movie> existingMovie = movieRepository.findByTitleAndYearAndDirector(movie.getTitle(), movie.getYear(), movie.getDirector());
            if (existingMovie.isPresent() && !existingMovie.get().getId().equals(movie.getId())) {
                validationErrors.add(MOVIE_NOT_UNIQUE);
            }
        }

        //In case there is at least one invalid field throw a validation exception
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }
}
