package com.vilgodskaia.movieplatform.core.service;

import com.vilgodskaia.movieplatform.core.config.exceptions.ValidationException;
import com.vilgodskaia.movieplatform.core.model.MovieOnStreamingPlatform;
import com.vilgodskaia.movieplatform.core.repository.MovieOnStreamingPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.vilgodskaia.movieplatform.core.util.ValidationUtil.checkNotNull;

@Component
@RequiredArgsConstructor
public class MovieOnStreamingPlatformValidator {
    public static final String MOVIE_NOT_AVAILABLE_ANYWHERE = "The movie should be available either for buying or in subscription or both";
    public static final String PRICE_FOR_BUYING_NOT_VALID = "The price for buying should not be lower than 0";
    public static final String AVAILABLE_UNTIL_NOT_VALID = "The expiration date should not be in the past";
    public static final String RELATION_NOT_UNIQUE = "The relation between these movie and streaming platform already exists";
    private final MovieOnStreamingPlatformRepository movieOnStreamingPlatformRepository;

    public void validateForCreating(MovieOnStreamingPlatform movieOnStreamingPlatform) {
        List<String> validationErrors = new ArrayList<>();
        validationErrors.addAll(checkNotNull("Movie ", movieOnStreamingPlatform.getMovie()));
        validationErrors.addAll(checkNotNull("StreamingPlatform ", movieOnStreamingPlatform.getStreamingPlatform()));
        if (movieOnStreamingPlatform.getMovie() != null
                && movieOnStreamingPlatform.getStreamingPlatform() != null
                && movieOnStreamingPlatformRepository
                .findByMovieAndStreamingPlatform(movieOnStreamingPlatform.getMovie(), movieOnStreamingPlatform.getStreamingPlatform()).isPresent()) {
            validationErrors.add(RELATION_NOT_UNIQUE);
        }
        validateMutableFields(movieOnStreamingPlatform, validationErrors);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }

    public void validateForUpdating(MovieOnStreamingPlatform movieOnStreamingPlatform) {
        List<String> validationErrors = new ArrayList<>();
        validateMutableFields(movieOnStreamingPlatform, validationErrors);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }

    private void validateMutableFields(MovieOnStreamingPlatform movieOnStreamingPlatform, List<String> validationErrors) {
        if (!movieOnStreamingPlatform.isAvailableForBuying() && !movieOnStreamingPlatform.isAvailableInSubscription()) {
            validationErrors.add(MOVIE_NOT_AVAILABLE_ANYWHERE);
        }
        if (movieOnStreamingPlatform.getPriceForBuying() != null && movieOnStreamingPlatform.getPriceForBuying() < 0) {
            validationErrors.add(PRICE_FOR_BUYING_NOT_VALID);
        }
        if (movieOnStreamingPlatform.getAvailableUntil() != null && movieOnStreamingPlatform.getAvailableUntil().isBefore(LocalDate.now())) {
            validationErrors.add(AVAILABLE_UNTIL_NOT_VALID);
        }
    }
}
