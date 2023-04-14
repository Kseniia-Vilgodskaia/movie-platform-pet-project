package com.vilgodskaia.movieplatform.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "movie_on_streaming_platform",
        uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "streaming_platform_id"}))
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
public class MovieOnStreamingPlatform {

    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id")
    private UUID id;

    /**
     * Movie
     */
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, foreignKey = @ForeignKey(name = "FK_movie_on_streaming_platform_movie"))
    private Movie movie;

    /**
     * Streaming platform
     */
    @ManyToOne
    @JoinColumn(name = "streaming_platform_id", nullable = false, foreignKey = @ForeignKey(name = "FK_movie_on_streaming_platform_streaming_platform"))
    private StreamingPlatform streamingPlatform;

    /**
     * Indicates if the movie is available for buying on the streaming platform
     */
    @Column(name = "available_for_buying", nullable = false)
    private boolean availableForBuying;

    /**
     * Indicates if the movie is available in the streaming platform subscription
     */
    @Column(name = "available_in_subscription", nullable = false)
    private boolean availableInSubscription;

    /**
     * Price for buying.
     * Can be null if the movie is not available for buying.
     */
    @Column(name = "price_for_buying")
    private Integer priceForBuying;

    /**
     * Date when the movie becomes unavailable on the streaming platform.
     * Can be null if the movie is going to be available forever.
     */
    @Column(name = "available_until")
    private LocalDate availableUntil;
}
