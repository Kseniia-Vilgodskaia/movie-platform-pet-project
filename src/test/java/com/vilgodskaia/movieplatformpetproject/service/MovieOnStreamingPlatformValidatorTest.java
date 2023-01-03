package com.vilgodskaia.movieplatformpetproject.service;

import com.vilgodskaia.movieplatformpetproject.model.Movie;
import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.repository.MovieOnStreamingPlatformRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.vilgodskaia.movieplatformpetproject.util.ValidationTestsUtil.validateFieldMultipleErrors;
import static com.vilgodskaia.movieplatformpetproject.util.ValidationTestsUtil.validateFieldSingleError;
import static com.vilgodskaia.movieplatformpetproject.util.ValidationUtil.FIELD_NULL_POSTFIX;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MovieOnStreamingPlatformValidatorTest {
    private MovieOnStreamingPlatform movieOnStreamingPlatform;
    private final MovieOnStreamingPlatformRepository repositoryMock = Mockito.mock(MovieOnStreamingPlatformRepository.class);
    private final MovieOnStreamingPlatformValidator validator =
            new MovieOnStreamingPlatformValidator(repositoryMock);
    private final Executable createExecutable = () -> validator.validateForCreating(movieOnStreamingPlatform);
    private final Executable updateExecutable = () -> validator.validateForUpdating(movieOnStreamingPlatform);

    @BeforeEach
    void setUp() {
        movieOnStreamingPlatform = new MovieOnStreamingPlatform()
                .setId(UUID.randomUUID())
                .setMovie(Mockito.mock(Movie.class))
                .setStreamingPlatform(Mockito.mock(StreamingPlatform.class))
                .setAvailableForBuying(true)
                .setAvailableInSubscription(true)
                .setPriceForBuying(700)
                .setAvailableUntil(LocalDate.MAX);
    }

    @Nested
    class validateForCreatingTest {
        @BeforeEach
        void setUp() {
            Mockito.when(repositoryMock.findByMovieAndStreamingPlatform(movieOnStreamingPlatform.getMovie(), movieOnStreamingPlatform.getStreamingPlatform()))
                    .thenReturn(Optional.empty());
        }

        @Test
        void should_NotThrowValidationException_when_CorrectMoviePlatformRelation() {
            assertDoesNotThrow(createExecutable);
        }

        @Test
        void should_ThrowValidationExceptionForNullMovie_when_MovieNull() {
            movieOnStreamingPlatform.setMovie(null);
            validateFieldSingleError("Movie " + FIELD_NULL_POSTFIX, createExecutable);
        }

        @Test
        void should_ThrowValidationExceptionForNullStreamingPlatform_when_StreamingPlatformNull() {
            movieOnStreamingPlatform.setStreamingPlatform(null);
            validateFieldSingleError("StreamingPlatform " + FIELD_NULL_POSTFIX, createExecutable);
        }

        @Test
        @DisplayName("Should throw a validation exception for not unique relation when there is another relation with these movie and streaming platform")
        void should_ThrowValidationExceptionForNotUniqueRelation_when_RelationNotUnique() {
            MovieOnStreamingPlatform anotherMovieOnStreamingPlatform = new MovieOnStreamingPlatform()
                    .setId(UUID.randomUUID())
                    .setMovie(movieOnStreamingPlatform.getMovie())
                    .setStreamingPlatform(movieOnStreamingPlatform.getStreamingPlatform());
            Mockito.when(repositoryMock.findByMovieAndStreamingPlatform(movieOnStreamingPlatform.getMovie(), movieOnStreamingPlatform.getStreamingPlatform()))
                    .thenReturn(Optional.of(anotherMovieOnStreamingPlatform));
            validateFieldSingleError(MovieOnStreamingPlatformValidator.RELATION_NOT_UNIQUE, createExecutable);
        }


        @Test
        @DisplayName("Should throw a validation exception for not available movie if both fields availableForBuying and availableInSubscription are FALSE")
        void should_ThrowValidationExceptionForNotAvailableMovie_when_MovieNotAvailable() {
            movieOnStreamingPlatform
                    .setAvailableForBuying(false)
                    .setAvailableInSubscription(false);
            validateFieldSingleError(MovieOnStreamingPlatformValidator.MOVIE_NOT_AVAILABLE_ANYWHERE, createExecutable);
        }

        @Test
        void should_ThrowValidationExceptionForNotValidPrice_when_PriceForBuyingLowerThan0() {
            movieOnStreamingPlatform.setPriceForBuying(-20);
            validateFieldSingleError(MovieOnStreamingPlatformValidator.PRICE_FOR_BUYING_NOT_VALID, createExecutable);
        }

        @Test
        void should_ThrowValidationExceptionForNotValidAvailableUntilDate_when_AvailableUntilDateInThePast() {
            movieOnStreamingPlatform.setAvailableUntil(LocalDate.MIN);
            validateFieldSingleError(MovieOnStreamingPlatformValidator.AVAILABLE_UNTIL_NOT_VALID, createExecutable);
        }

        @Test
        @DisplayName("should throw validation exception with list of errors when a movie is null and buying price is lower than 0")
        void should_ThrowValidationExceptionWithListOfErrors_when_MultipleFieldsNotValid() {
            movieOnStreamingPlatform
                    .setMovie(null)
                    .setPriceForBuying(-100);
            List<String> expectedErrorMessages = List.of(
                    "Movie " + FIELD_NULL_POSTFIX,
                    MovieOnStreamingPlatformValidator.PRICE_FOR_BUYING_NOT_VALID
            );
            validateFieldMultipleErrors(expectedErrorMessages, createExecutable);
        }
    }

    @Nested
    class validateForUpdatingTest {
        @Test
        void should_NotThrowValidationException_when_CorrectMoviePlatformRelation() {
            assertDoesNotThrow(updateExecutable);
        }

        @Test
        @DisplayName("Should throw a validation exception for not available movie if both fields availableForBuying and availableInSubscription are FALSE")
        void should_ThrowValidationExceptionForNotAvailableMovie_when_MovieNotAvailable() {
            movieOnStreamingPlatform
                    .setAvailableForBuying(false)
                    .setAvailableInSubscription(false);
            validateFieldSingleError(MovieOnStreamingPlatformValidator.MOVIE_NOT_AVAILABLE_ANYWHERE, updateExecutable);
        }

        @Test
        void should_ThrowValidationExceptionForNotValidPrice_when_PriceForBuyingLowerThan0() {
            movieOnStreamingPlatform.setPriceForBuying(-20);
            validateFieldSingleError(MovieOnStreamingPlatformValidator.PRICE_FOR_BUYING_NOT_VALID, updateExecutable);
        }

        @Test
        void should_ThrowValidationExceptionForNotValidAvailableUntilDate_when_AvailableUntilDateInThePast() {
            movieOnStreamingPlatform.setAvailableUntil(LocalDate.MIN);
            validateFieldSingleError(MovieOnStreamingPlatformValidator.AVAILABLE_UNTIL_NOT_VALID, updateExecutable);
        }

        @Test
        @DisplayName("should throw validation exception with list of errors when a movie is not available anywhere and expiry date is not valid")
        void should_ThrowValidationExceptionWithListOfErrors_when_MultipleFieldsNotValid() {
            movieOnStreamingPlatform
                    .setAvailableForBuying(false)
                    .setAvailableInSubscription(false)
                    .setAvailableUntil(LocalDate.MIN);
            List<String> expectedErrorsMessages = List.of(
                    MovieOnStreamingPlatformValidator.MOVIE_NOT_AVAILABLE_ANYWHERE,
                    MovieOnStreamingPlatformValidator.AVAILABLE_UNTIL_NOT_VALID
            );
            validateFieldMultipleErrors(expectedErrorsMessages, updateExecutable);
        }
    }
}