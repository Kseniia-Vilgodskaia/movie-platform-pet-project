package com.example.movieplatformpetproject.service;

import com.example.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformInputDto;
import com.example.movieplatformpetproject.model.StreamingPlatform;
import com.example.movieplatformpetproject.repository.StreamingPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamingPlatformService {

    private final StreamingPlatformRepository streamingPlatformRepository;

    /**
     * Create a new streaming platform
     *
     * @param streamingPlatformInputDto - streamingPlatformInputDto of the streaming platform
     * @return - created streaming platform
     */
    public StreamingPlatform create(StreamingPlatformInputDto streamingPlatformInputDto) {
        StreamingPlatform streamingPlatform = new StreamingPlatform()
                .setName(streamingPlatformInputDto.getName());
        return streamingPlatformRepository.save(streamingPlatform);
    }
}
