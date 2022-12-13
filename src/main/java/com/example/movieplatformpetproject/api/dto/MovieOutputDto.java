package com.example.movieplatformpetproject.api.dto;

import com.example.movieplatformpetproject.model.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
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
