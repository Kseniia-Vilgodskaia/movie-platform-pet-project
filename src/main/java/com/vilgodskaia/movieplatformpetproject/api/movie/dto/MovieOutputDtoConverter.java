package com.vilgodskaia.movieplatformpetproject.api.movie.dto;


import com.vilgodskaia.movieplatformpetproject.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieOutputDtoConverter {

    public MovieOutputDto convert(Movie movie) {
        return new MovieOutputDto()
                .setId(movie.getId())
                .setTitle(movie.getTitle())
                .setYear(movie.getYear())
                .setGenre(movie.getGenre())
                .setDuration(movie.getDuration())
                .setDirector(movie.getDirector());
    }
}
