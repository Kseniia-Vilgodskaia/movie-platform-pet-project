package com.vilgodskaia.movieplatformpetproject.service;

import com.vilgodskaia.movieplatformpetproject.config.exceptions.ValidationException;
import com.vilgodskaia.movieplatformpetproject.model.Movie;
import com.vilgodskaia.movieplatformpetproject.model.MovieGenre;
import com.vilgodskaia.movieplatformpetproject.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.vilgodskaia.movieplatformpetproject.util.ValidationTestsUtil.checkFieldValidationSingleError;
import static com.vilgodskaia.movieplatformpetproject.util.ValidationUtil.FIELD_EMPTY_POSTFIX;
import static com.vilgodskaia.movieplatformpetproject.util.ValidationUtil.FIELD_NULL_POSTFIX;
import static org.junit.jupiter.api.Assertions.*;

class MovieValidatorTest {

    private Movie movie;
    private final MovieRepository movieRepositoryMock = Mockito.mock(MovieRepository.class);
    private final MovieValidator movieValidator = new MovieValidator(movieRepositoryMock);
    private final Executable movieValidationExecutable = () -> movieValidator.validate(movie);

    @BeforeEach
    void setUp() {
        movie = new Movie()
                .setId(UUID.randomUUID())
                .setTitle("How to Lose a Guy in 10 Days")
                .setYear(2003)
                .setGenre(MovieGenre.ROMANCE)
                .setDuration(116)
                .setDirector("Donald Petrie");
        Mockito.when(movieRepositoryMock.findByTitleAndYearAndDirector(movie.getTitle(), movie.getYear(), movie.getDirector()))
                .thenReturn(Optional.empty());
    }

    @Test
    void should_NotThrowMovieValidationException_when_CorrectUniqueMovie() {
        assertDoesNotThrow(() -> movieValidator.validate(movie));
    }

    @Test
    void should_ThrowMovieValidationExceptionForTitleNullField_when_TitleNull() {
        movie.setTitle(null);
        checkFieldValidationSingleError("Title" + FIELD_NULL_POSTFIX, movieValidationExecutable);
    }

    @Test
    void should_ThrowMovieValidationExceptionForTitleEmptyField_when_TitleEmpty() {
        movie.setTitle("");
        checkFieldValidationSingleError("Title" + FIELD_EMPTY_POSTFIX, movieValidationExecutable);
    }

    @Test
    void should_ThrowMovieValidationExceptionForYearNullField_when_YearNull() {
        movie.setYear(null);
        checkFieldValidationSingleError("Year" + FIELD_NULL_POSTFIX, movieValidationExecutable);
    }

    @Test
    void should_ThrowMovieValidationExceptionForYearNotCorrectField_when_YearLessThan1895() {
        movie.setYear(1232);
        checkFieldValidationSingleError(MovieValidator.YEAR_NOT_VALID, movieValidationExecutable);
    }

    @Test
    void should_ThrowMovieValidationExceptionForGenreNullField_when_GenreNull() {
        movie.setGenre(null);
        checkFieldValidationSingleError("Genre" + FIELD_NULL_POSTFIX, movieValidationExecutable);
    }

    @Test
    void should_ThrowMovieValidationExceptionForDurationNullField_when_DurationNull() {
        movie.setDuration(null);
        checkFieldValidationSingleError("Duration" + FIELD_NULL_POSTFIX, movieValidationExecutable);
    }

    @Test
    void should_ThrowMovieValidationExceptionForDurationNotCorrectField_when_DurationLessThanOrEqualTo0() {
        movie.setDuration(-12);
        checkFieldValidationSingleError(MovieValidator.DURATION_NOT_VALID, movieValidationExecutable);
    }

    @Test
    void should_ThrowMovieValidationExceptionForDirectorNullField_when_DirectorNull() {
        movie.setDirector(null);
        checkFieldValidationSingleError("Director" + FIELD_NULL_POSTFIX, movieValidationExecutable);
    }

    @Test
    void should_ThrowMovieValidationExceptionForDirectorEmptyField_when_DirectorEmpty() {
        movie.setDirector("");
        checkFieldValidationSingleError("Director" + FIELD_EMPTY_POSTFIX, movieValidationExecutable);
    }

    @Test
    void should_ThrowMovieValidationExceptionForNotUniqueMovie_when_AnotherMovieWithTitleAndYearAndDirectorExistsInDb() {
        Movie anotherMovie = new Movie()
                .setId(UUID.randomUUID())
                .setTitle(movie.getTitle())
                .setYear(movie.getYear())
                .setGenre(MovieGenre.DRAMA)
                .setDuration(120)
                .setDirector(movie.getDirector());

        Mockito.when(movieRepositoryMock.findByTitleAndYearAndDirector(movie.getTitle(), movie.getYear(), movie.getDirector()))
                .thenReturn(Optional.of(anotherMovie));
        checkFieldValidationSingleError(MovieValidator.MOVIE_NOT_UNIQUE, movieValidationExecutable);
    }

    @Test
    void should_NotThrowMovieValidationException_when_ChangingExistingMovie() {
        Mockito.when(movieRepositoryMock.findByTitleAndYearAndDirector(movie.getTitle(), movie.getYear(), movie.getDirector()))
                .thenReturn(Optional.of(movie));
        assertDoesNotThrow(() -> movieValidator.validate(movie));
    }

    @Test
    void should_ThrowMovieValidationExceptionForYearAndDurationFields_when_NotCorrectYearAndDuration() {
        movie.setYear(1036);
        movie.setDuration(0);
        List<String> expectedErrorMessages = List.of(
                MovieValidator.YEAR_NOT_VALID,
                MovieValidator.DURATION_NOT_VALID);
        ValidationException exception = assertThrows(ValidationException.class, () -> movieValidator.validate(movie));
        assertAll(
                () -> assertEquals(expectedErrorMessages.size(), exception.getValidationErrors().size()),
                () -> assertTrue(exception.getValidationErrors().containsAll(expectedErrorMessages))
        );

    }
}