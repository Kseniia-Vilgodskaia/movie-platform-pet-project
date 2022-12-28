package com.example.movieplatformpetproject.api;

import com.example.movieplatformpetproject.api.movie.MovieValidationException;
import com.example.movieplatformpetproject.api.movie.MovieValidator;
import com.example.movieplatformpetproject.model.Movie;
import com.example.movieplatformpetproject.model.MovieGenre;
import com.example.movieplatformpetproject.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MovieValidatorTest {

    private Movie movie;
    private final MovieRepository movieRepositoryMock = Mockito.mock(MovieRepository.class);
    private final MovieValidator movieValidator = new MovieValidator(movieRepositoryMock);

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
        String expectedErrorMessage = "Title" + MovieValidator.FIELD_NULL_POSTFIX;
        checkFieldValidationSingleError(expectedErrorMessage);
    }

    @Test
    void should_ThrowMovieValidationExceptionForTitleEmptyField_when_TitleEmpty() {
        movie.setTitle("");
        String expectedErrorMessage = "Title" + MovieValidator.FIELD_EMPTY_POSTFIX;
        checkFieldValidationSingleError(expectedErrorMessage);
    }

    @Test
    void should_ThrowMovieValidationExceptionForYearNullField_when_YearNull() {
        movie.setYear(null);
        String expectedErrorMessage = "Year" + MovieValidator.FIELD_NULL_POSTFIX;
        checkFieldValidationSingleError(expectedErrorMessage);
    }

    @Test
    void should_ThrowMovieValidationExceptionForYearNotCorrectField_when_YearLessThan1895() {
        movie.setYear(1232);
        String expectedErrorMessage = MovieValidator.YEAR_NOT_VALID;
        checkFieldValidationSingleError(expectedErrorMessage);
    }

    @Test
    void should_ThrowMovieValidationExceptionForGenreNullField_when_GenreNull() {
        movie.setGenre(null);
        String expectedErrorMessage = "Genre" + MovieValidator.FIELD_NULL_POSTFIX;
        checkFieldValidationSingleError(expectedErrorMessage);
    }

    @Test
    void should_ThrowMovieValidationExceptionForDurationNullField_when_DurationNull() {
        movie.setDuration(null);
        String expectedErrorMessage = "Duration" + MovieValidator.FIELD_NULL_POSTFIX;
        checkFieldValidationSingleError(expectedErrorMessage);
    }

    @Test
    void should_ThrowMovieValidationExceptionForDurationNotCorrectField_when_DurationLessThanOrEqualTo0() {
        movie.setDuration(-12);
        String expectedErrorMessage = MovieValidator.DURATION_NOT_VALID;
        checkFieldValidationSingleError(expectedErrorMessage);
    }

    @Test
    void should_ThrowMovieValidationExceptionForDirectorNullField_when_DirectorNull() {
        movie.setDirector(null);
        String expectedErrorMessage = "Director" + MovieValidator.FIELD_NULL_POSTFIX;
        checkFieldValidationSingleError(expectedErrorMessage);
    }

    @Test
    void should_ThrowMovieValidationExceptionForDirectorEmptyField_when_DirectorEmpty() {
        movie.setDirector("");
        String expectedErrorMessage = "Director" + MovieValidator.FIELD_EMPTY_POSTFIX;
        checkFieldValidationSingleError(expectedErrorMessage);
    }

    @Test
    void should_ThrowMovieValidationExceptionForNotUniqueMovie_when_AnotherMovieWithTitleAndYearAndDirectorExistsInDb() {
        Movie storedMovie = new Movie()
                .setId(UUID.randomUUID())
                .setTitle(movie.getTitle())
                .setYear(movie.getYear())
                .setGenre(MovieGenre.DRAMA)
                .setDuration(120)
                .setDirector(movie.getDirector());

        Mockito.when(movieRepositoryMock.findByTitleAndYearAndDirector(movie.getTitle(), movie.getYear(), movie.getDirector()))
                .thenReturn(Optional.of(storedMovie));
        assertAll(
                () -> assertNotEquals(movie.getId(), storedMovie.getId()),
                () -> assertEquals(storedMovie.getTitle(), movie.getTitle()),
                () -> assertEquals(storedMovie.getYear(), movie.getYear()),
                () -> assertEquals(storedMovie.getDirector(), movie.getDirector())
        );

        String expectedErrorMessage = MovieValidator.MOVIE_NOT_UNIQUE;
        checkFieldValidationSingleError(expectedErrorMessage);
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
        MovieValidationException exception = assertThrows(MovieValidationException.class, () -> movieValidator.validate(movie));
        assertAll(
                () -> assertEquals(expectedErrorMessages.size(), exception.getValidationErrors().size()),
                () -> assertTrue(exception.getValidationErrors().containsAll(expectedErrorMessages))
        );

    }

    private void checkFieldValidationSingleError(String expectedErrorMessage) {
        MovieValidationException exception = assertThrows(MovieValidationException.class, () -> movieValidator.validate(movie));
        assertAll(
                () -> assertEquals(1, exception.getValidationErrors().size()),
                () -> assertEquals(expectedErrorMessage, exception.getValidationErrors().get(0))
        );
    }
}