package com.vilgodskaia.movieplatformpetproject.service;

import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformInputDto;
import com.vilgodskaia.movieplatformpetproject.model.Movie;
import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.repository.MovieOnStreamingPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieOnStreamingPlatformService {
    private final MovieOnStreamingPlatformRepository movieOnStreamingPlatformRepository;
    private final MovieService movieService;
    private final StreamingPlatformService streamingPlatformService;

    /**
     * Create a new relation between a movie and a streaming platform
     *
     * @param movieOnStreamingPlatformInputDto - MovieOnStreamingPlatformInputDto of the relation with Movie ID and Streaming Platform ID
     * @return - MovieOnStreamingPlatform of the created relation
     */
    public MovieOnStreamingPlatform create(MovieOnStreamingPlatformInputDto movieOnStreamingPlatformInputDto) {
        Movie movie = movieService.get(movieOnStreamingPlatformInputDto.getMovieId());
        StreamingPlatform streamingPlatform = streamingPlatformService.get(movieOnStreamingPlatformInputDto.getStreamingPlatformId());
        MovieOnStreamingPlatform movieOnStreamingPlatform = new MovieOnStreamingPlatform()
                .setMovie(movie)
                .setStreamingPlatform(streamingPlatform)
                .setAvailableForBuying(movieOnStreamingPlatformInputDto.isAvailableForBuying())
                .setAvailableInSubscription(movieOnStreamingPlatformInputDto.isAvailableInSubscription())
                .setPriceForBuying(movieOnStreamingPlatformInputDto.getPriceForBuying())
                .setAvailableUntil(movieOnStreamingPlatformInputDto.getAvailableUntil());
        return movieOnStreamingPlatformRepository.save(movieOnStreamingPlatform);
    }
}
