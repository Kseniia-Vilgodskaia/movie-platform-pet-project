package com.vilgodskaia.movieplatform.core.service;

import com.vilgodskaia.movieplatform.core.api.movie.dto.MovieFilter;
import com.vilgodskaia.movieplatform.core.api.movie.dto.MovieInputDto;
import com.vilgodskaia.movieplatform.core.model.Movie;
import com.vilgodskaia.movieplatform.core.repository.MovieOnStreamingPlatformRepository;
import com.vilgodskaia.movieplatform.core.repository.MovieRepository;
import com.vilgodskaia.movieplatform.core.repository.MovieSpecificationsFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
     * Get a page with movies from DB with optional filter
     *
     * @param filter    - fields to filter with
     * @param page      - page number
     * @param size      - number of movies on one page
     * @param sort      - field(s) for sorting
     * @param direction - order of displaying
     * @return - a page of movies
     */
    public Page<Movie> filter(MovieFilter filter, Integer page, Integer size, String sort, Sort.Direction direction) {
        return movieRepository.findAll(
                MovieSpecificationsFactory.filter(filter),
                PageRequest.of(page, size, Sort.by(direction, sort.split(","))));
    }

    /**
     * Find a movie by its ID
     *
     * @param id - ID of the movie
     * @return - MovieOutputDto of the found movie
     */
    public Movie get(UUID id) {
        return movieRepository.getOrThrow(id);
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
