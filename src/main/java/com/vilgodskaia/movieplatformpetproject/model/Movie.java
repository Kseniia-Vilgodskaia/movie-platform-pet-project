package com.vilgodskaia.movieplatformpetproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "movie",
        uniqueConstraints = @UniqueConstraint(columnNames = {"title", "year", "director"}))
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
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
    private Integer year;

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
    private Integer duration;

    /**
     * Director's first and last name
     */
    @Column(name = "director", nullable = false)
    private String director;
}
