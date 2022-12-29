package com.vilgodskaia.movieplatformpetproject.service;

import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.repository.StreamingPlatformRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static com.vilgodskaia.movieplatformpetproject.util.ValidationTestsUtil.checkFieldValidationSingleError;
import static com.vilgodskaia.movieplatformpetproject.util.ValidationUtil.FIELD_EMPTY_POSTFIX;
import static com.vilgodskaia.movieplatformpetproject.util.ValidationUtil.FIELD_NULL_POSTFIX;
import static org.junit.jupiter.api.Assertions.*;

class StreamingPlatformValidatorTest {
    private StreamingPlatform streamingPlatform;
    private final StreamingPlatformRepository streamingPlatformRepository = Mockito.mock(StreamingPlatformRepository.class);
    private final StreamingPlatformValidator streamingPlatformValidator = new StreamingPlatformValidator(streamingPlatformRepository);
    private final Executable streamingPlatformExecutable = () -> streamingPlatformValidator.validate(streamingPlatform);

    @BeforeEach
    void setUp() {
        streamingPlatform = new StreamingPlatform()
                .setId(UUID.randomUUID())
                .setName("OKKO");
        Mockito.when(streamingPlatformRepository.findByName(streamingPlatform.getName()))
                .thenReturn(Optional.empty());
    }

    @Test
    void should_NotThrowStreamingPlatformValidationException_when_CorrectUniqueStreamingPlatform() {
        assertDoesNotThrow(() -> streamingPlatformValidator.validate(streamingPlatform));
    }

    @Test
    void should_ThrowStreamingPlatformValidationExceptionForNameNullField_when_NameNull() {
        streamingPlatform.setName(null);
        checkFieldValidationSingleError("Name" + FIELD_NULL_POSTFIX, streamingPlatformExecutable);
    }

    @Test
    void should_ThrowStreamingPlatformValidationExceptionForNameEmptyField_when_NameEmpty() {
        streamingPlatform.setName("");
        checkFieldValidationSingleError("Name" + FIELD_EMPTY_POSTFIX, streamingPlatformExecutable);
    }

    @Test
    void should_ThrowStreamingPlatformValidationExceptionForNotUniqueStreamingPlatform_when_AnotherStreamingPlatformWithNameExistsInDb() {
        StreamingPlatform anotherStreamingPlatform = new StreamingPlatform()
                .setId(UUID.randomUUID())
                .setName(streamingPlatform.getName());
        Mockito.when(streamingPlatformRepository.findByName(streamingPlatform.getName()))
                .thenReturn(Optional.of(anotherStreamingPlatform));
        assertAll(
                () -> assertNotEquals(anotherStreamingPlatform.getId(), streamingPlatform.getId()),
                () -> assertEquals(anotherStreamingPlatform.getName(), streamingPlatform.getName())
        );
        checkFieldValidationSingleError(StreamingPlatformValidator.STREAMING_PLATFORM_NOT_UNIQUE, streamingPlatformExecutable);
    }

    @Test
    void should_NotThrowStreamingPlatformValidationException_when_ChangingExistingStreamingPlatform() {
        Mockito.when(streamingPlatformRepository.findByName(streamingPlatform.getName()))
                .thenReturn(Optional.of(streamingPlatform));
        assertDoesNotThrow(() -> streamingPlatformValidator.validate(streamingPlatform));
    }
}