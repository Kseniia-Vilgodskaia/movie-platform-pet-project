package com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MovieOnStreamingPlatformUpdateInputDto {

    private boolean availableForBuying;
    private boolean availableInSubscription;
    private Integer priceForBuying;
    private LocalDate availableUntil;
}
