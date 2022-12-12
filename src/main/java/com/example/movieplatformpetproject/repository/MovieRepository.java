package com.example.movieplatformpetproject.repository;

import com.example.movieplatformpetproject.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
