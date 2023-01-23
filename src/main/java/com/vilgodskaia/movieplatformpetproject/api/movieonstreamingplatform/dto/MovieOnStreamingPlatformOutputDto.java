package com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto;

import com.vilgodskaia.movieplatformpetproject.api.movie.dto.MovieOutputDto;
import com.vilgodskaia.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformOutputDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MovieOnStreamingPlatformOutputDto {
    private UUID id;
    private MovieOutputDto movieOutputDto;
    private StreamingPlatformOutputDto streamingPlatformOutputDto;
    private boolean availableForBuying;
    private boolean availableInSubscription;
    private Integer priceForBuying;
    private LocalDate availableUntil;
}
