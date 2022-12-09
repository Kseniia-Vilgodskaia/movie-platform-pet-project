package com.example.movieplatformpetproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "movie")
@Getter
@NoArgsConstructor
public class Movie {

    /**
     * Id
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    /**
     * Title
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Release year
     */
    @Column(name = "year", nullable = false)
    private int year;

    /**
     * Genre
     */
    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    /**
     * Duration in minutes
     */
    @Column(name = "duration", nullable = false)
    private int duration;

    /**
     * Director's first and last name
     */
    @Column(name = "director", nullable = false)
    private String director;

    public Movie(String title, int year, MovieGenre genre, int duration, String director) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
    }
}
