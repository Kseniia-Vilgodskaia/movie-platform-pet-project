package com.example.movieplatformpetproject.api.movie.dto;

import com.example.movieplatformpetproject.model.MovieGenre;
import lombok.Getter;

@Getter
public class MovieInputDto {

    /**
     * Title
     */
    private String title;

    /**
     * Release year
     */
    private Integer year;

    /**
     * Genre
     */
    private MovieGenre genre;

    /**
     * Duration in minutes
     */
    private Integer duration;

    /**
     * Director's first and last name
     */
    private String director;
}
