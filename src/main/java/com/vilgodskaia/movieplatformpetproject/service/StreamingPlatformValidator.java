package com.vilgodskaia.movieplatformpetproject.service;

import com.vilgodskaia.movieplatformpetproject.config.exceptions.StreamingPlatformValidationException;
import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.repository.StreamingPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.vilgodskaia.movieplatformpetproject.util.ValidationUtil.checkNotEmptyAndNotNull;

@Component
@RequiredArgsConstructor
public class StreamingPlatformValidator {
    public static final String STREAMING_PLATFORM_NOT_UNIQUE = "A streaming platform with this name already exists";
    private final StreamingPlatformRepository streamingPlatformRepository;

    public void validate(StreamingPlatform streamingPlatform) {
        List<String> errorMessages = new ArrayList<>();
        String streamingPlatformName = streamingPlatform.getName();
        checkNotEmptyAndNotNull("Name", streamingPlatformName, errorMessages);

        if (streamingPlatformName != null) {
            Optional<StreamingPlatform> existingPlatform = streamingPlatformRepository.findByName(streamingPlatformName);
            if (existingPlatform.isPresent() && !existingPlatform.get().getId().equals(streamingPlatform.getId())) {
                errorMessages.add(STREAMING_PLATFORM_NOT_UNIQUE);
            }
        }

        if (!errorMessages.isEmpty()) {
            throw new StreamingPlatformValidationException(errorMessages);
        }
    }
}
