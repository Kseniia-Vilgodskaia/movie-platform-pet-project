package com.vilgodskaia.movieplatformpetproject.repository;

import com.vilgodskaia.movieplatformpetproject.config.exceptions.MovieNotFoundException;
import com.vilgodskaia.movieplatformpetproject.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID>, JpaSpecificationExecutor<Movie> {

    /**
     * Find an existing movie by its Title, Release Year and Director's name
     *
     * @param title    - Title
     * @param year     - Release Year
     * @param director - Director's first and last name
     * @return - an existing movie from DB
     */
    Optional<Movie> findByTitleAndYearAndDirector(String title, Integer year, String director);

    default Movie getOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }
}
