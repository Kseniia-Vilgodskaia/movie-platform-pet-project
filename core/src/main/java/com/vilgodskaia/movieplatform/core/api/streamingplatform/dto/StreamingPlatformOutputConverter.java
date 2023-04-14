package com.vilgodskaia.movieplatform.core.api.streamingplatform.dto;

import com.vilgodskaia.movieplatform.core.model.StreamingPlatform;
import org.springframework.stereotype.Component;

@Component
public class StreamingPlatformOutputConverter {

    public StreamingPlatformOutputDto convert(StreamingPlatform streamingPlatform) {
        return new StreamingPlatformOutputDto()
                .setId(streamingPlatform.getId())
                .setName(streamingPlatform.getName());
    }
}
