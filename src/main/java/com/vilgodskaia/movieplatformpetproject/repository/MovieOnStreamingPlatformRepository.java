package com.vilgodskaia.movieplatformpetproject.repository;

import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieOnStreamingPlatformRepository extends JpaRepository<MovieOnStreamingPlatform, UUID> {
}
