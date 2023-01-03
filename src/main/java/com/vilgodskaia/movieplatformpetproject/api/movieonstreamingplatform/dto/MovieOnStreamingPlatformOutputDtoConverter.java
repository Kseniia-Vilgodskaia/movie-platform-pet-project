package com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto;

import com.vilgodskaia.movieplatformpetproject.api.movie.dto.MovieOutputDtoConverter;
import com.vilgodskaia.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformOutputConverter;
import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
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
