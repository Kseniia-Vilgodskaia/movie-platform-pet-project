package com.example.movieplatformpetproject.api.streamingplatform.dto;

import com.example.movieplatformpetproject.model.StreamingPlatform;
import org.springframework.stereotype.Component;

@Component
public class StreamingPlatformOutputConverter {

    public StreamingPlatformOutputDto convert(StreamingPlatform streamingPlatform) {
        return new StreamingPlatformOutputDto()
                .setId(streamingPlatform.getId())
                .setName(streamingPlatform.getName());
    }
}
