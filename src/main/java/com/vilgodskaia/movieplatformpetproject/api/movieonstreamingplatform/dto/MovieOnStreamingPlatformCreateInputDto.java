package com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class MovieOnStreamingPlatformCreateInputDto {

    private UUID movieId;
    private UUID streamingPlatformId;
    private boolean availableForBuying;
    private boolean availableInSubscription;
    private Integer priceForBuying;
    private LocalDate availableUntil;
}
