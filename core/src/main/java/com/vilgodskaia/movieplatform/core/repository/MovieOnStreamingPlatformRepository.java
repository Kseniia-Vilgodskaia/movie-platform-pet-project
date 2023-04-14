package com.vilgodskaia.movieplatform.core.repository;

import com.vilgodskaia.movieplatform.core.config.exceptions.EntityNotFoundException;
import com.vilgodskaia.movieplatform.core.model.EntityType;
import com.vilgodskaia.movieplatform.core.model.Movie;
import com.vilgodskaia.movieplatform.core.model.MovieOnStreamingPlatform;
import com.vilgodskaia.movieplatform.core.model.StreamingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MovieOnStreamingPlatformRepository extends JpaRepository<MovieOnStreamingPlatform, UUID> {

    /**
     * Find a relation between a movie and a streaming platform by Movie and Streaming platform entities
     *
     * @param movie             - Movie entity
     * @param streamingPlatform - Streaming platform entity
     * @return - Optional of the relation from DB
     */
    Optional<MovieOnStreamingPlatform> findByMovieAndStreamingPlatform(Movie movie, StreamingPlatform streamingPlatform);

    /**
     * Delete a relation between a movie and a streaming platform by Movie ID
     *
     * @param movieId - Movie ID
     */
    void deleteByMovieId(UUID movieId);

    /**
     * Delete a relation between a movie and a streaming platform by Streaming platform ID
     *
     * @param streamingPlatformId - Streaming platform ID
     */
    void deleteByStreamingPlatformId(UUID streamingPlatformId);

    /**
     * Find a relation between a movie and a streaming platform by its ID or throw an EntityNotFoundException in case relation not found
     *
     * @param id - Relation ID
     * @return - an existing relation from DB
     */
    default MovieOnStreamingPlatform getOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException(EntityType.MOVIE_ON_STREAMING_PLATFORM, id));
    }
}
