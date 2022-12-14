package com.example.movieplatformpetproject.service;

import com.example.movieplatformpetproject.api.dto.MovieInputDto;
import com.example.movieplatformpetproject.model.Movie;
import com.example.movieplatformpetproject.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Movie movie = new Movie()
                .setTitle(movieInputDto.getTitle())
                .setYear(movieInputDto.getYear())
                .setGenre(movieInputDto.getGenre())
                .setDuration(movieInputDto.getDuration())
                .setDirector(movieInputDto.getDirector());
        return movieRepository.save(movie);
    }

    /**
     * Find all movies from DB
     *
     * @return - List of movies (MovieOutputDto)
     */
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    /**
     * Find a movie by its ID
     *
     * @param id - ID of the movie
     * @return - MovieOutputDto of the found movie
     */
    public Movie get(UUID id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found by ID: " + id));
    }

    /**
     * Update an existing movie
     *
     * @param id            - ID of the movie
     * @param movieInputDto - MovieInputDto of the movie
     * @return - MovieOutputDto of updated movie
     */
    public Movie update(UUID id, MovieInputDto movieInputDto) {
        Movie movie = get(id);
        movie.setTitle(movieInputDto.getTitle())
                .setYear(movieInputDto.getYear())
                .setGenre(movieInputDto.getGenre())
                .setDuration(movieInputDto.getDuration())
                .setDirector(movieInputDto.getDirector());
        return movieRepository.save(movie);
    }


    /**
     * Delete an existing movie
     *
     * @param id - ID of the movie
     */
    public void delete(UUID id) {
        movieRepository.deleteById(id);
    }
}
