package com.example.movieplatformpetproject.api.streamingplatform.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
public class StreamingPlatformOutputDto {

    /**
     * Id
     */
    private UUID id;

    /**
     * Name
     */
    private String name;
}
