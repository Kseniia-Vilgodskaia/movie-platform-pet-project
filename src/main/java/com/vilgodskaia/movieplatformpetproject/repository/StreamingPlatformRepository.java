package com.vilgodskaia.movieplatformpetproject.repository;

import com.vilgodskaia.movieplatformpetproject.config.exceptions.EntityNotFoundException;
import com.vilgodskaia.movieplatformpetproject.model.EntityType;
import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;


public interface StreamingPlatformRepository extends JpaRepository<StreamingPlatform, UUID>, JpaSpecificationExecutor<StreamingPlatform> {
    /**
     * Find an existing streaming platform by its name (name column has a unique constraint)
     *
     * @param name - Streaming platform name
     * @return - Optional of an existing streaming platform from DB
     */
    Optional<StreamingPlatform> findByName(String name);

    /**
     * Find an existing streaming platform by its id or throw an EntityNotFoundException in case streaming platform not found
     *
     * @param id - Streaming platform ID
     * @return - an existing streaming platform from DB
     */
    default StreamingPlatform getOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException(EntityType.STREAMING_PLATFORM, id));
    }
}
