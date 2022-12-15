package com.example.movieplatformpetproject.service;

import com.example.movieplatformpetproject.api.MovieNotFoundException;
import com.example.movieplatformpetproject.api.MovieValidator;
import com.example.movieplatformpetproject.api.dto.MovieInputDto;
import com.example.movieplatformpetproject.model.Movie;
import com.example.movieplatformpetproject.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieValidator movieValidator;

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
        movieValidator.validate(movie);
        return movieRepository.save(movie);
    }

    /**
     * Get a page with movies from DB
     * @param page - page number
     * @param size - number of movies on one page
     * @param sort - field(s) for sorting
     * @param direction - order of displaying
     * @return - a page of movies
     */
    public Page<Movie> getPage(Integer page, Integer size, String sort, Sort.Direction direction) {
        return movieRepository.findAll(PageRequest.of(page, size, Sort.by(direction, sort.split(","))));
    }

    /**
     * Find a movie by its ID
     *
     * @param id - ID of the movie
     * @return - MovieOutputDto of the found movie
     */
    public Movie get(UUID id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie not found by ID: " + id));
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
        movieValidator.validate(movie);
        return movieRepository.save(movie);
    }


    /**
     * Delete an existing movie
     *
     * @param id - ID of the movie
     */
    public void delete(UUID id) {
        movieRepository.delete(get(id));
    }
}
