package com.vilgodskaia.movieplatformpetproject.repository;

import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface StreamingPlatformRepository extends JpaRepository<StreamingPlatform, UUID> {
    Optional<StreamingPlatform> findByName(String name);
}
