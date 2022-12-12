package com.example.movieplatformpetproject.dto;

import com.example.movieplatformpetproject.model.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class MovieOutputDto {

    /**
     * Id
     */
    private UUID id;

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
