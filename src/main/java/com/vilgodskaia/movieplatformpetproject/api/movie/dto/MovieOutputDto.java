package com.vilgodskaia.movieplatformpetproject.api.movie.dto;

import com.vilgodskaia.movieplatformpetproject.model.MovieGenre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
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
