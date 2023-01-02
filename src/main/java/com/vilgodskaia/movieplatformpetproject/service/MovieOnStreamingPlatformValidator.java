package com.vilgodskaia.movieplatformpetproject.service;

import com.vilgodskaia.movieplatformpetproject.config.exceptions.ValidationException;
import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.repository.MovieOnStreamingPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.vilgodskaia.movieplatformpetproject.util.ValidationUtil.checkNotNull;

@Component
@RequiredArgsConstructor
public class MovieOnStreamingPlatformValidator {
    private static final String MOVIE_NOT_AVAILABLE_ANYWHERE = "The movie should be available either for buying or in subscription or both";
    private static final String PRICE_FOR_BUYING_NOT_VALID = "The price for buying should not be lower than 0";
    private static final String AVAILABLE_UNTIL_NOT_VALID = "The expiration date should not be in the past";
    private static final String RELATION_NOT_UNIQUE = "The relation between these movie and streaming platform already exists";
    private final MovieOnStreamingPlatformRepository movieOnStreamingPlatformRepository;

    public void validateForCreating(MovieOnStreamingPlatform movieOnStreamingPlatform) {
        List<String> validationErrors = new ArrayList<>();
        checkNotNull("Movie ", movieOnStreamingPlatform.getMovie(), validationErrors);
        checkNotNull("StreamingPlatform ", movieOnStreamingPlatform.getStreamingPlatform(), validationErrors);
        if (movieOnStreamingPlatform.getMovie() != null
                && movieOnStreamingPlatform.getStreamingPlatform() != null
                && movieOnStreamingPlatformRepository
                .findByMovieAndStreamingPlatform(movieOnStreamingPlatform.getMovie(), movieOnStreamingPlatform.getStreamingPlatform()).isPresent()) {
            validationErrors.add(RELATION_NOT_UNIQUE);
        }
        validateAvailabilityAndPriceAndExpiryDate(movieOnStreamingPlatform, validationErrors);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }

    public void validateForUpdating(MovieOnStreamingPlatform movieOnStreamingPlatform) {
        List<String> validationErrors = new ArrayList<>();
        validateAvailabilityAndPriceAndExpiryDate(movieOnStreamingPlatform, validationErrors);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }

    private void validateAvailabilityAndPriceAndExpiryDate(MovieOnStreamingPlatform movieOnStreamingPlatform, List<String> validationErrors) {
        if (!movieOnStreamingPlatform.isAvailableForBuying() && !movieOnStreamingPlatform.isAvailableInSubscription()) {
            validationErrors.add(MOVIE_NOT_AVAILABLE_ANYWHERE);
        }
        checkNotNull("PriceForBuying ", movieOnStreamingPlatform.getPriceForBuying(), validationErrors);
        if (movieOnStreamingPlatform.getPriceForBuying() != null && movieOnStreamingPlatform.getPriceForBuying() < 0) {
            validationErrors.add(PRICE_FOR_BUYING_NOT_VALID);
        }
        checkNotNull("AvailableUntil ", movieOnStreamingPlatform.getAvailableUntil(), validationErrors);
        if (movieOnStreamingPlatform.getAvailableUntil() != null && movieOnStreamingPlatform.getAvailableUntil().isBefore(LocalDate.now())) {
            validationErrors.add(AVAILABLE_UNTIL_NOT_VALID);
        }
    }
}
