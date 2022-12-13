package com.example.movieplatformpetproject.service;

import com.example.movieplatformpetproject.api.dto.MovieInputDto;
import com.example.movieplatformpetproject.model.Movie;
import com.example.movieplatformpetproject.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    /**
     * Create a new movie
     *
     * @param movieInputDto - MovieInputDto of the movie
     * @return - MovieOutputDto of created movie
     */
    public Movie create(MovieInputDto movieInputDto) {
        Movie movie = Movie.builder()
                .title(movieInputDto.getTitle())
                .year(movieInputDto.getYear())
                .genre(movieInputDto.getGenre())
                .duration(movieInputDto.getDuration())
                .director(movieInputDto.getDirector())
                .build();
        return movieRepository.save(movie);
    }

    /**
     * Find all movies from DB
     * @return - List of movies (MovieOutputDto)
     */
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * Find a movie by its ID
     * @param id - ID of the movie
     * @return - MovieOutputDto of the found movie
     */
    public Movie findById(UUID id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new RuntimeException("Movie not found by ID: " + id);
        }
        return movie.get();
    }

    /**
     * Update an existing movie
     * @param id - ID of the movie
     * @param movieInputDto - MovieInputDto of the movie
     * @return - MovieOutputDto of updated movie
     */
    public Movie update(UUID id, MovieInputDto movieInputDto) {
        if (movieRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Movie not found by ID: " + id);
        }
        Movie movie = Movie.builder()
                .id(id)
                .title(movieInputDto.getTitle())
                .year(movieInputDto.getYear())
                .genre(movieInputDto.getGenre())
                .duration(movieInputDto.getDuration())
                .director(movieInputDto.getDirector())
                .build();
        return movieRepository.save(movie);
    }


    /**
     * Delete an existing movie
     * @param id - ID of the movie
     */
    public void delete(UUID id) {
        movieRepository.deleteById(id);
    }
}
