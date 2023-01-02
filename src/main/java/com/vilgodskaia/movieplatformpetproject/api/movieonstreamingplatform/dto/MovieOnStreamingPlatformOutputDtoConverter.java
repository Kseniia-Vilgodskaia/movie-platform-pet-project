package com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto;

import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
import org.springframework.stereotype.Component;

@Component
public class MovieOnStreamingPlatformOutputDtoConverter {

    public MovieOnStreamingPlatformOutputDto convert(MovieOnStreamingPlatform movieOnStreamingPlatform) {
        return new MovieOnStreamingPlatformOutputDto()
                .setId(movieOnStreamingPlatform.getId())
                .setMovie(movieOnStreamingPlatform.getMovie())
                .setStreamingPlatform(movieOnStreamingPlatform.getStreamingPlatform())
                .setAvailableForBuying(movieOnStreamingPlatform.isAvailableForBuying())
                .setAvailableInSubscription(movieOnStreamingPlatform.isAvailableInSubscription())
                .setPriceForBuying(movieOnStreamingPlatform.getPriceForBuying())
                .setAvailableUntil(movieOnStreamingPlatform.getAvailableUntil());
    }
}
