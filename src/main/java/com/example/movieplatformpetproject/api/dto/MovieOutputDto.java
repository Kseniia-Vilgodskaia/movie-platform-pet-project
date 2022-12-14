package com.example.movieplatformpetproject.api.dto;

import com.example.movieplatformpetproject.model.MovieGenre;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
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
