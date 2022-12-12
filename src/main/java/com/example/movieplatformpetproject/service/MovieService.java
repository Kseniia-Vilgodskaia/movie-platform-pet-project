package com.example.movieplatformpetproject.service;

import com.example.movieplatformpetproject.dto.MovieInputDto;
import com.example.movieplatformpetproject.dto.MovieOutputDto;
import com.example.movieplatformpetproject.dto.MovieOutputDtoConverter;
import com.example.movieplatformpetproject.model.Movie;
import com.example.movieplatformpetproject.model.MovieBuilder;
import com.example.movieplatformpetproject.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieOutputDtoConverter movieOutputDtoConverter;

    /**
     * Create a new movie
     * @param movieInputDto - MovieInputDto of the movie
     * @return - MovieOutputDto of created movie
     */
    public MovieOutputDto create(MovieInputDto movieInputDto) {
        Movie movie = new MovieBuilder()
                .withTitle(movieInputDto.getTitle())
                .withYear(movieInputDto.getYear())
                .withGenre(movieInputDto.getGenre())
                .withDuration(movieInputDto.getDuration())
                .withDirector(movieInputDto.getDirector())
                .build();
        movieRepository.save(movie);
        return movieOutputDtoConverter.convert(movie);
    }
}
