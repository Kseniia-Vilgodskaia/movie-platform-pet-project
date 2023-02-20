package com.vilgodskaia.movieplatformpetproject.repository;

import com.vilgodskaia.movieplatformpetproject.config.exceptions.MovieOnStreamingPlatformNotFoundException;
import com.vilgodskaia.movieplatformpetproject.model.Movie;
import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MovieOnStreamingPlatformRepository extends JpaRepository<MovieOnStreamingPlatform, UUID> {

    Optional<MovieOnStreamingPlatform> findByMovieAndStreamingPlatform(Movie movie, StreamingPlatform streamingPlatform);

    void deleteByMovieId(UUID movieId);

    void deleteByStreamingPlatformId(UUID streamingPlatformId);

    default MovieOnStreamingPlatform getOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new MovieOnStreamingPlatformNotFoundException(id));
    }
}
