package com.vilgodskaia.movieplatformpetproject.service;

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

import static com.vilgodskaia.movieplatformpetproject.util.ValidationTestsUtil.validateFieldMultipleErrors;
import static com.vilgodskaia.movieplatformpetproject.util.ValidationTestsUtil.validateFieldSingleError;
import static com.vilgodskaia.movieplatformpetproject.util.ValidationUtil.FIELD_EMPTY_POSTFIX;
import static com.vilgodskaia.movieplatformpetproject.util.ValidationUtil.FIELD_NULL_POSTFIX;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MovieValidatorTest {

    private Movie movie;
    private final MovieRepository repositoryMock = Mockito.mock(MovieRepository.class);
    private final MovieValidator validator = new MovieValidator(repositoryMock);
    private final Executable executable = () -> validator.validate(movie);

    @BeforeEach
    void setUp() {
        movie = new Movie()
                .setId(UUID.randomUUID())
                .setTitle("How to Lose a Guy in 10 Days")
                .setYear(2003)
                .setGenre(MovieGenre.ROMANCE)
                .setDuration(116)
                .setDirector("Donald Petrie");
        Mockito.when(repositoryMock.findByTitleAndYearAndDirector(movie.getTitle(), movie.getYear(), movie.getDirector()))
                .thenReturn(Optional.empty());
    }

    @Test
    void should_NotThrowValidationException_when_CorrectUniqueMovie() {
        assertDoesNotThrow(() -> validator.validate(movie));
    }

    @Test
    void should_ThrowValidationExceptionForTitleNullField_when_TitleNull() {
        movie.setTitle(null);
        validateFieldSingleError("Title" + FIELD_NULL_POSTFIX, executable);
    }

    @Test
    void should_ThrowValidationExceptionForTitleEmptyField_when_TitleEmpty() {
        movie.setTitle("");
        validateFieldSingleError("Title" + FIELD_EMPTY_POSTFIX, executable);
    }

    @Test
    void should_ThrowValidationExceptionForYearNullField_when_YearNull() {
        movie.setYear(null);
        validateFieldSingleError("Year" + FIELD_NULL_POSTFIX, executable);
    }

    @Test
    void should_ThrowValidationExceptionForYearNotCorrectField_when_YearLessThan1895() {
        movie.setYear(1232);
        validateFieldSingleError(MovieValidator.YEAR_NOT_VALID, executable);
    }

    @Test
    void should_ThrowValidationExceptionForGenreNullField_when_GenreNull() {
        movie.setGenre(null);
        validateFieldSingleError("Genre" + FIELD_NULL_POSTFIX, executable);
    }

    @Test
    void should_ThrowValidationExceptionForDurationNullField_when_DurationNull() {
        movie.setDuration(null);
        validateFieldSingleError("Duration" + FIELD_NULL_POSTFIX, executable);
    }

    @Test
    void should_ThrowValidationExceptionForDurationNotCorrectField_when_DurationLessThanOrEqualTo0() {
        movie.setDuration(-12);
        validateFieldSingleError(MovieValidator.DURATION_NOT_VALID, executable);
    }

    @Test
    void should_ThrowValidationExceptionForDirectorNullField_when_DirectorNull() {
        movie.setDirector(null);
        validateFieldSingleError("Director" + FIELD_NULL_POSTFIX, executable);
    }

    @Test
    void should_ThrowValidationExceptionForDirectorEmptyField_when_DirectorEmpty() {
        movie.setDirector("");
        validateFieldSingleError("Director" + FIELD_EMPTY_POSTFIX, executable);
    }

    @Test
    void should_ThrowValidationExceptionForNotUniqueMovie_when_AnotherMovieWithTitleAndYearAndDirectorExistsInDb() {
        Movie anotherMovie = new Movie()
                .setId(UUID.randomUUID())
                .setTitle(movie.getTitle())
                .setYear(movie.getYear())
                .setGenre(MovieGenre.DRAMA)
                .setDuration(120)
                .setDirector(movie.getDirector());

        Mockito.when(repositoryMock.findByTitleAndYearAndDirector(movie.getTitle(), movie.getYear(), movie.getDirector()))
                .thenReturn(Optional.of(anotherMovie));
        validateFieldSingleError(MovieValidator.MOVIE_NOT_UNIQUE, executable);
    }

    @Test
    void should_NotThrowValidationException_when_ChangingExistingMovie() {
        Mockito.when(repositoryMock.findByTitleAndYearAndDirector(movie.getTitle(), movie.getYear(), movie.getDirector()))
                .thenReturn(Optional.of(movie));
        assertDoesNotThrow(executable);
    }

    @Test
    void should_ThrowValidationExceptionForYearAndDurationFields_when_NotCorrectYearAndDuration() {
        movie.setYear(1036);
        movie.setDuration(0);
        List<String> expectedErrorMessages = List.of(
                MovieValidator.YEAR_NOT_VALID,
                MovieValidator.DURATION_NOT_VALID);
        validateFieldMultipleErrors(expectedErrorMessages, executable);
    }
}