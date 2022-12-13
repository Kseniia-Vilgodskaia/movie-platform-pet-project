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

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(UUID id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new RuntimeException("Movie not found by ID: " + id);
        }
        return movie.get();
    }
}
