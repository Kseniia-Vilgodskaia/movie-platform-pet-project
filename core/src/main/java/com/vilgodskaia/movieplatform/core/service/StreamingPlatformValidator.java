package com.vilgodskaia.movieplatform.core.service;

import com.vilgodskaia.movieplatform.core.config.exceptions.ValidationException;
import com.vilgodskaia.movieplatform.core.model.StreamingPlatform;
import com.vilgodskaia.movieplatform.core.repository.StreamingPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.vilgodskaia.movieplatform.core.util.ValidationUtil.checkNotEmptyAndNotNull;

@Component
@RequiredArgsConstructor
public class StreamingPlatformValidator {
    public static final String STREAMING_PLATFORM_NOT_UNIQUE = "A streaming platform with this name already exists";
    private final StreamingPlatformRepository streamingPlatformRepository;

    public void validate(StreamingPlatform streamingPlatform) {
        String streamingPlatformName = streamingPlatform.getName();
        List<String> validationErrors = new ArrayList<>(checkNotEmptyAndNotNull("Name", streamingPlatformName));

        if (streamingPlatformName != null) {
            Optional<StreamingPlatform> existingPlatform = streamingPlatformRepository.findByName(streamingPlatformName);
            if (existingPlatform.isPresent() && !existingPlatform.get().getId().equals(streamingPlatform.getId())) {
                validationErrors.add(STREAMING_PLATFORM_NOT_UNIQUE);
            }
        }

        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
    }
}
