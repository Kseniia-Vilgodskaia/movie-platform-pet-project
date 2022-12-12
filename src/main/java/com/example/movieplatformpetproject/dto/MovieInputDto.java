package com.example.movieplatformpetproject.dto;

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
    private int year;

    /**
     * Genre
     */
    private MovieGenre genre;

    /**
     * Duration in minutes
     */
    private int duration;

    /**
     * Director's first and last name
     */
    private String director;
}
