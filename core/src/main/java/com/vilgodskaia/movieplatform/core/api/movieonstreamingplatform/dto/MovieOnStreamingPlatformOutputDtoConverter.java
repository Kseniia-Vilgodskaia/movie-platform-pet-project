package com.vilgodskaia.movieplatform.core.api.movieonstreamingplatform.dto;

import com.vilgodskaia.movieplatform.core.api.movie.dto.MovieOutputDtoConverter;
import com.vilgodskaia.movieplatform.core.api.streamingplatform.dto.StreamingPlatformOutputConverter;
import com.vilgodskaia.movieplatform.core.model.MovieOnStreamingPlatform;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieOnStreamingPlatformOutputDtoConverter {

    private final MovieOutputDtoConverter movieOutputDtoConverter;
    private final StreamingPlatformOutputConverter streamingPlatformOutputConverter;

    public MovieOnStreamingPlatformOutputDto convert(MovieOnStreamingPlatform movieOnStreamingPlatform) {
        return new MovieOnStreamingPlatformOutputDto()
                .setId(movieOnStreamingPlatform.getId())
                .setMovieOutputDto(movieOutputDtoConverter.convert(movieOnStreamingPlatform.getMovie()))
                .setStreamingPlatformOutputDto(streamingPlatformOutputConverter.convert(movieOnStreamingPlatform.getStreamingPlatform()))
                .setAvailableForBuying(movieOnStreamingPlatform.isAvailableForBuying())
                .setAvailableInSubscription(movieOnStreamingPlatform.isAvailableInSubscription())
                .setPriceForBuying(movieOnStreamingPlatform.getPriceForBuying())
                .setAvailableUntil(movieOnStreamingPlatform.getAvailableUntil());
    }
}
