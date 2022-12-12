package com.example.movieplatformpetproject.dto;


import com.example.movieplatformpetproject.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieOutputDtoConverter {

    public MovieOutputDto convert(Movie movie) {
        return new MovieOutputDto(
                movie.getId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getGenre(),
                movie.getDuration(),
                movie.getDirector());
    }
}
