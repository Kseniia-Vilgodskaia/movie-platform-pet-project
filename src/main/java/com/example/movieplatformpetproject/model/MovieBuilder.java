package com.example.movieplatformpetproject.model;

/**
 * Class for building a Movie entity
 */
public class MovieBuilder {

    private String title;
    private int year;
    private MovieGenre genre;
    private int duration;
    private String director;

    public MovieBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MovieBuilder withYear(int year) {
        this.year = year;
        return this;
    }

    public MovieBuilder withGenre(MovieGenre genre) {
        this.genre = genre;
        return this;
    }

    public MovieBuilder withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public MovieBuilder withDirector(String director) {
        this.director = director;
        return this;
    }

    public Movie build() {
        return new Movie(title, year, genre, duration, director);
    }
}
