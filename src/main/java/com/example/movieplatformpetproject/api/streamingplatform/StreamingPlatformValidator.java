package com.example.movieplatformpetproject.api.streamingplatform;

import com.example.movieplatformpetproject.model.StreamingPlatform;
import com.example.movieplatformpetproject.repository.StreamingPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StreamingPlatformValidator {
    private final StreamingPlatformRepository streamingPlatformRepository;

    public static final String NAME_NULL_POSTFIX = "Streaming platform name should not be null";
    public static final String NAME_EMPTY_POSTFIX = "Streaming platform name should not be empty";
    public static final String STREAMING_PLATFORM_NOT_UNIQUE = "A streaming platform with this name already exists";

    public void validate(StreamingPlatform streamingPlatform) {
        List<String> errorMessages = new ArrayList<>();
        String streamingPlatformName = streamingPlatform.getName();
        checkNameNotNullOrEmpty(streamingPlatformName, errorMessages);

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

    private void checkNameNotNullOrEmpty(String streamingPlatformName, List<String> errorMessages) {
        if (streamingPlatformName == null) {
            errorMessages.add(NAME_NULL_POSTFIX);
        } else if (streamingPlatformName.isEmpty()) {
            errorMessages.add(NAME_EMPTY_POSTFIX);
        }
    }
}
