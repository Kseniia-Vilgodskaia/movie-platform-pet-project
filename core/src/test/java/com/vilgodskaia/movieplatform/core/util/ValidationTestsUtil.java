package com.vilgodskaia.movieplatform.core.util;

import com.vilgodskaia.movieplatform.core.config.exceptions.ValidationException;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTestsUtil {
    public static void validateFieldSingleError(String expectedErrorMessage, Executable executable) {
        ValidationException exception = assertThrows
                (ValidationException.class, executable);
        assertAll(
                () -> assertEquals(1, exception.getValidationErrors().size()),
                () -> assertEquals(expectedErrorMessage, exception.getValidationErrors().get(0))
        );
    }

    public static void validateFieldMultipleErrors(List<String> expectedErrorMessages, Executable executable) {
        ValidationException exception = assertThrows(ValidationException.class, executable);
        assertAll(
                () -> assertEquals(expectedErrorMessages.size(), exception.getValidationErrors().size()),
                () -> assertTrue(exception.getValidationErrors().containsAll(expectedErrorMessages))
        );
    }
}
