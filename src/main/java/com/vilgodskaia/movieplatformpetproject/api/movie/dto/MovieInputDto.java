package com.vilgodskaia.movieplatformpetproject.api.movie.dto;

import com.vilgodskaia.movieplatformpetproject.model.MovieGenre;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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
