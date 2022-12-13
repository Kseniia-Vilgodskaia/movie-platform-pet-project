package com.example.movieplatformpetproject.api.dto;


import com.example.movieplatformpetproject.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieOutputDtoConverter {

    public MovieOutputDto convert(Movie movie) {
        return MovieOutputDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .year(movie.getYear())
                .genre(movie.getGenre())
                .duration(movie.getDuration())
                .director(movie.getDirector())
                .build();
    }
}
