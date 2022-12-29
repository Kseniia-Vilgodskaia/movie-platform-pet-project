package com.vilgodskaia.movieplatformpetproject.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "movie_on_streaming_platform")
public class MovieOnStreamingPlatform {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id")
    private UUID id;


    private Movie movie;
    private StreamingPlatform streamingPlatform;

    @Column(name = "available_for_buying")
    private boolean availableForBuying;

    @Column(name = "available_in_subscription")
    private boolean availableInSubscription;

    @Column(name = "price_for_buying")
    private Integer priceForBuying;

    @Column(name = "available_until")
    private LocalDate availableUntil;
}
