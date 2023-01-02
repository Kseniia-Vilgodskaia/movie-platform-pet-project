package com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto;

import com.vilgodskaia.movieplatformpetproject.model.Movie;
import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
public class MovieOnStreamingPlatformOutputDto {
    private UUID id;
    private Movie movie;
    private StreamingPlatform streamingPlatform;
    private boolean availableForBuying;
    private boolean availableInSubscription;
    private Integer priceForBuying;
    private LocalDate availableUntil;
}
