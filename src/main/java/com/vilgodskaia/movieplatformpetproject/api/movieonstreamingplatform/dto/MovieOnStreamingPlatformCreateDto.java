package com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
public class MovieOnStreamingPlatformCreateDto {

    private UUID movieId;
    private UUID streamingPlatformId;
    private boolean availableForBuying;
    private boolean availableInSubscription;
    private Integer priceForBuying;
    private LocalDate availableUntil;
}
