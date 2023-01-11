package com.vilgodskaia.movieplatformpetproject.service;

import com.vilgodskaia.movieplatformpetproject.api.movie.dto.MovieInputDto;
import com.vilgodskaia.movieplatformpetproject.config.exceptions.MovieNotFoundException;
import com.vilgodskaia.movieplatformpetproject.model.Movie;
import com.vilgodskaia.movieplatformpetproject.repository.MovieOnStreamingPlatformRepository;
import com.vilgodskaia.movieplatformpetproject.repository.MovieRepository;
import jakarta.transaction.Transactional;
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
    private final MovieOnStreamingPlatformRepository movieOnStreamingPlatformRepository;
    private final MovieValidator movieValidator;

    /**
     * Create a new movie
     *
     * @param movieInputDto - MovieInputDto of the movie
     * @return - created movie
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
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
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
     * Delete an existing movie and all relations with streaming platforms that is has
     *
     * @param id - ID of the movie
     */
    @Transactional
    public void delete(UUID id) {
        movieOnStreamingPlatformRepository.deleteByMovieId(id);
        movieRepository.delete(get(id));
    }
}
