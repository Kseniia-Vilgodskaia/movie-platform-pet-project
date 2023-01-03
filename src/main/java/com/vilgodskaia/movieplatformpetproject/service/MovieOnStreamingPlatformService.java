package com.vilgodskaia.movieplatformpetproject.service;

import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformCreateDto;
import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformUpdateDto;
import com.vilgodskaia.movieplatformpetproject.config.exceptions.MovieOnStreamingPlatformNotFoundException;
import com.vilgodskaia.movieplatformpetproject.model.Movie;
import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.repository.MovieOnStreamingPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieOnStreamingPlatformService {
    private final MovieOnStreamingPlatformRepository movieOnStreamingPlatformRepository;
    private final MovieOnStreamingPlatformValidator movieOnStreamingPlatformValidator;
    private final MovieService movieService;
    private final StreamingPlatformService streamingPlatformService;

    /**
     * Create a new relation between a movie and a streaming platform
     *
     * @param movieOnStreamingPlatformCreateDto - MovieOnStreamingPlatformCreateDto of the relation with Movie ID and Streaming Platform ID
     * @return - MovieOnStreamingPlatform of the created relation
     */
    public MovieOnStreamingPlatform create(MovieOnStreamingPlatformCreateDto movieOnStreamingPlatformCreateDto) {
        Movie movie = movieService.get(movieOnStreamingPlatformCreateDto.getMovieId());
        StreamingPlatform streamingPlatform = streamingPlatformService.get(movieOnStreamingPlatformCreateDto.getStreamingPlatformId());
        MovieOnStreamingPlatform movieOnStreamingPlatform = new MovieOnStreamingPlatform()
                .setMovie(movie)
                .setStreamingPlatform(streamingPlatform)
                .setAvailableForBuying(movieOnStreamingPlatformCreateDto.isAvailableForBuying())
                .setAvailableInSubscription(movieOnStreamingPlatformCreateDto.isAvailableInSubscription())
                .setPriceForBuying(movieOnStreamingPlatformCreateDto.getPriceForBuying())
                .setAvailableUntil(movieOnStreamingPlatformCreateDto.getAvailableUntil());
        movieOnStreamingPlatformValidator.validateForCreating(movieOnStreamingPlatform);
        return movieOnStreamingPlatformRepository.save(movieOnStreamingPlatform);
    }

    /**
     * Get a page of relations between movies and streaming platforms
     *
     * @return - a page of relations
     */
    public Page<MovieOnStreamingPlatform> getPage(Integer page, Integer size, String sort, Sort.Direction direction) {
        return movieOnStreamingPlatformRepository.findAll(PageRequest.of(page, size, direction, sort.split(",")));
    }

    /**
     * Get a specific relation between a movie and a streaming platform by ID
     *
     * @param id - ID of the relation
     * @return - found relation
     */
    public MovieOnStreamingPlatform get(UUID id) {
        return movieOnStreamingPlatformRepository.findById(id).orElseThrow(() -> new MovieOnStreamingPlatformNotFoundException(id));
    }

    /**
     * Update an existing relation between a movie and a streaming platform by ID
     *
     * @param id                                - ID of the relation
     * @param movieOnStreamingPlatformUpdateDto - MovieOnStreamingPlatformUpdateDto with new data for the relation
     * @return - updated relation
     */
    public MovieOnStreamingPlatform update(UUID id, MovieOnStreamingPlatformUpdateDto movieOnStreamingPlatformUpdateDto) {
        MovieOnStreamingPlatform movieOnStreamingPlatform = get(id)
                .setAvailableForBuying(movieOnStreamingPlatformUpdateDto.isAvailableForBuying())
                .setAvailableInSubscription(movieOnStreamingPlatformUpdateDto.isAvailableInSubscription())
                .setPriceForBuying(movieOnStreamingPlatformUpdateDto.getPriceForBuying())
                .setAvailableUntil(movieOnStreamingPlatformUpdateDto.getAvailableUntil());
        movieOnStreamingPlatformValidator.validateForUpdating(movieOnStreamingPlatform);
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
