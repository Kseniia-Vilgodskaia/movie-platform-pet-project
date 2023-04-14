package com.vilgodskaia.movieplatform.core.api.movieonstreamingplatform.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class MovieOnStreamingPlatformUpdateDto {

    private boolean availableForBuying;
    private boolean availableInSubscription;
    private Integer priceForBuying;
    private LocalDate availableUntil;
}
