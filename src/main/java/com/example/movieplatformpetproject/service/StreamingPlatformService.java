package com.example.movieplatformpetproject.service;

import com.example.movieplatformpetproject.api.streamingplatform.StreamingPlatformNotFoundException;
import com.example.movieplatformpetproject.api.streamingplatform.StreamingPlatformValidator;
import com.example.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformInputDto;
import com.example.movieplatformpetproject.model.StreamingPlatform;
import com.example.movieplatformpetproject.repository.StreamingPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StreamingPlatformService {

    private final StreamingPlatformRepository streamingPlatformRepository;
    private final StreamingPlatformValidator streamingPlatformValidator;

    /**
     * Create a new streaming platform
     *
     * @param streamingPlatformInputDto - streamingPlatformInputDto of the streaming platform
     * @return - created streaming platform
     */
    public StreamingPlatform create(StreamingPlatformInputDto streamingPlatformInputDto) {
        StreamingPlatform streamingPlatform = new StreamingPlatform()
                .setName(streamingPlatformInputDto.getName());
        streamingPlatformValidator.validate(streamingPlatform);
        return streamingPlatformRepository.save(streamingPlatform);
    }

    /**
     * Get all existing streaming platforms
     *
     * @return - list of all streaming platforms
     */
    public List<StreamingPlatform> getAll() {
        return streamingPlatformRepository.findAll();
    }

    /**
     * Get an existing streaming platform by its ID
     *
     * @param id - ID of the streaming platform
     * @return - found streaming platform
     */
    public StreamingPlatform get(UUID id) {
        return streamingPlatformRepository
                .findById(id)
                .orElseThrow(() -> new StreamingPlatformNotFoundException("Streaming Platform not found by id: " + id));
    }

    /**
     * Update an existing streaming platform
     *
     * @param id                        - ID of the streaming platform
     * @param streamingPlatformInputDto - streamingPlatformInputDto of the streaming platform
     * @return - updated streaming platform
     */
    public StreamingPlatform update(UUID id, StreamingPlatformInputDto streamingPlatformInputDto) {
        StreamingPlatform streamingPlatform = get(id);
        streamingPlatform.setName(streamingPlatformInputDto.getName());
        streamingPlatformValidator.validate(streamingPlatform);
        return streamingPlatformRepository.save(streamingPlatform);
    }

    /**
     * Delete an existing streaming platform
     *
     * @param id - ID of the streaming platform
     */
    public void delete(UUID id) {
        StreamingPlatform streamingPlatform = get(id);
        streamingPlatformRepository.delete(streamingPlatform);
    }
}
