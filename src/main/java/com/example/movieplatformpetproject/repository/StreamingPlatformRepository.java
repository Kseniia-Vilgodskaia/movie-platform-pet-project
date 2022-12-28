package com.example.movieplatformpetproject.repository;

import com.example.movieplatformpetproject.model.StreamingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface StreamingPlatformRepository extends JpaRepository<StreamingPlatform, UUID> {
}
