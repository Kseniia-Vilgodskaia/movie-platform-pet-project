package com.vilgodskaia.movieplatformpetproject.service;

import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformCreateInputDto;
import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformUpdateInputDto;
import com.vilgodskaia.movieplatformpetproject.model.Movie;
import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.repository.MovieOnStreamingPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieOnStreamingPlatformService {
    private final MovieOnStreamingPlatformRepository movieOnStreamingPlatformRepository;
    private final MovieService movieService;
    private final StreamingPlatformService streamingPlatformService;

    /**
     * Create a new relation between a movie and a streaming platform
     *
     * @param movieOnStreamingPlatformCreateInputDto - MovieOnStreamingPlatformInputDto of the relation with Movie ID and Streaming Platform ID
     * @return - MovieOnStreamingPlatform of the created relation
     */
    public MovieOnStreamingPlatform create(MovieOnStreamingPlatformCreateInputDto movieOnStreamingPlatformCreateInputDto) {
        Movie movie = movieService.get(movieOnStreamingPlatformCreateInputDto.getMovieId());
        StreamingPlatform streamingPlatform = streamingPlatformService.get(movieOnStreamingPlatformCreateInputDto.getStreamingPlatformId());
        MovieOnStreamingPlatform movieOnStreamingPlatform = new MovieOnStreamingPlatform()
                .setMovie(movie)
                .setStreamingPlatform(streamingPlatform)
                .setAvailableForBuying(movieOnStreamingPlatformCreateInputDto.isAvailableForBuying())
                .setAvailableInSubscription(movieOnStreamingPlatformCreateInputDto.isAvailableInSubscription())
                .setPriceForBuying(movieOnStreamingPlatformCreateInputDto.getPriceForBuying())
                .setAvailableUntil(movieOnStreamingPlatformCreateInputDto.getAvailableUntil());
        return movieOnStreamingPlatformRepository.save(movieOnStreamingPlatform);
    }

    /**
     * Get all relations between movies and streaming platforms
     *
     * @return - the list of relations
     */
    public List<MovieOnStreamingPlatform> getAll() {
        return movieOnStreamingPlatformRepository.findAll();
    }

    /**
     * Get a specific relation between a movie and a streaming platform by ID
     *
     * @param id - ID of the relation
     * @return - found relation
     */
    public MovieOnStreamingPlatform get(UUID id) {
        return movieOnStreamingPlatformRepository.findById(id).orElseThrow();
    }

    /**
     * Update an existing relation between a movie and a streaming platform by ID
     *
     * @param id                                     - ID of the relation
     * @param movieOnStreamingPlatformUpdateInputDto - MovieOnStreamingPlatformUpdateInputDto with new data for the relation
     * @return - updated relation
     */
    public MovieOnStreamingPlatform update(UUID id, MovieOnStreamingPlatformUpdateInputDto movieOnStreamingPlatformUpdateInputDto) {
        MovieOnStreamingPlatform movieOnStreamingPlatform = get(id)
                .setAvailableForBuying(movieOnStreamingPlatformUpdateInputDto.isAvailableForBuying())
                .setAvailableInSubscription(movieOnStreamingPlatformUpdateInputDto.isAvailableInSubscription())
                .setPriceForBuying(movieOnStreamingPlatformUpdateInputDto.getPriceForBuying())
                .setAvailableUntil(movieOnStreamingPlatformUpdateInputDto.getAvailableUntil());
        return movieOnStreamingPlatformRepository.save(movieOnStreamingPlatform);
    }

    /**
     * Delete an existing relation between a movie and a streaming platform by ID
     *
     * @param id - ID of the relation
     */
    public void delete(UUID id) {
        movieOnStreamingPlatformRepository.delete(get(id));
    }
}
