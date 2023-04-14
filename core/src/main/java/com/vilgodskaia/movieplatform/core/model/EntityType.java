package com.vilgodskaia.movieplatform.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EntityType {
    MOVIE("Movie"),
    STREAMING_PLATFORM("Streaming platform"),
    MOVIE_ON_STREAMING_PLATFORM("Relation between a movie and a streaming platform");

    private final String name;
}
