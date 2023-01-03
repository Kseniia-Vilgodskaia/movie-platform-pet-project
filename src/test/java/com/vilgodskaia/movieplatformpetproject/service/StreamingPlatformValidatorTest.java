package com.vilgodskaia.movieplatformpetproject.service;

import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.repository.StreamingPlatformRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static com.vilgodskaia.movieplatformpetproject.util.ValidationTestsUtil.validateFieldSingleError;
import static com.vilgodskaia.movieplatformpetproject.util.ValidationUtil.FIELD_EMPTY_POSTFIX;
import static com.vilgodskaia.movieplatformpetproject.util.ValidationUtil.FIELD_NULL_POSTFIX;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StreamingPlatformValidatorTest {
    private StreamingPlatform streamingPlatform;
    private final StreamingPlatformRepository repositoryMock = Mockito.mock(StreamingPlatformRepository.class);
    private final StreamingPlatformValidator validator = new StreamingPlatformValidator(repositoryMock);
    private final Executable executable = () -> validator.validate(streamingPlatform);

    @BeforeEach
    void setUp() {
        streamingPlatform = new StreamingPlatform()
                .setId(UUID.randomUUID())
                .setName("OKKO");
        Mockito.when(repositoryMock.findByName(streamingPlatform.getName()))
                .thenReturn(Optional.empty());
    }

    @Test
    void should_NotThrowValidationException_when_CorrectUniqueStreamingPlatform() {
        assertDoesNotThrow(() -> validator.validate(streamingPlatform));
    }

    @Test
    void should_ThrowValidationExceptionForNameNullField_when_NameNull() {
        streamingPlatform.setName(null);
        validateFieldSingleError("Name" + FIELD_NULL_POSTFIX, executable);
    }

    @Test
    void should_ThrowValidationExceptionForNameEmptyField_when_NameEmpty() {
        streamingPlatform.setName("");
        validateFieldSingleError("Name" + FIELD_EMPTY_POSTFIX, executable);
    }

    @Test
    @DisplayName("Should throw a validation exception for not unique streaming platform when there is another streaming platform with this name")
    void should_ThrowValidationExceptionForNotUniqueStreamingPlatform_when_StreamingPlatformNotUnique() {
        StreamingPlatform anotherStreamingPlatform = new StreamingPlatform()
                .setId(UUID.randomUUID())
                .setName(streamingPlatform.getName());
        Mockito.when(repositoryMock.findByName(streamingPlatform.getName()))
                .thenReturn(Optional.of(anotherStreamingPlatform));
        validateFieldSingleError(StreamingPlatformValidator.STREAMING_PLATFORM_NOT_UNIQUE, executable);
    }

    @Test
    void should_NotThrowValidationException_when_ChangingExistingStreamingPlatform() {
        Mockito.when(repositoryMock.findByName(streamingPlatform.getName()))
                .thenReturn(Optional.of(streamingPlatform));
        assertDoesNotThrow(() -> validator.validate(streamingPlatform));
    }
}