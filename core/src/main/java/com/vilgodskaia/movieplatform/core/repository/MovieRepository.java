package com.vilgodskaia.movieplatform.core.repository;

import com.vilgodskaia.movieplatform.core.config.exceptions.EntityNotFoundException;
import com.vilgodskaia.movieplatform.core.model.EntityType;
import com.vilgodskaia.movieplatform.core.model.Movie;
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
     * @return - Optional of an existing movie from DB
     */
    Optional<Movie> findByTitleAndYearAndDirector(String title, Integer year, String director);

    /**
     * Find an existing movie by its id or throw an EntityNotFoundException in case movie not found
     *
     * @param id - Movie ID
     * @return - an existing movie from DB
     */
    default Movie getOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException(EntityType.MOVIE, id));
    }
}
