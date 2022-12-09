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

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private MovieGenre genre;

    @Column(name = "duration", nullable = false)
    private int duration; //in minutes

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
