package com.vilgodskaia.movieplatformpetproject.repository;

import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;


public interface StreamingPlatformRepository extends JpaRepository<StreamingPlatform, UUID>, JpaSpecificationExecutor<StreamingPlatform> {
    Optional<StreamingPlatform> findByName(String name);
}
