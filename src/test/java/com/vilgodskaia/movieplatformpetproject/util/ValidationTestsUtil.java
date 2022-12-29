package com.vilgodskaia.movieplatformpetproject.util;

import com.vilgodskaia.movieplatformpetproject.config.exceptions.ValidationException;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTestsUtil {
    public static void checkFieldValidationSingleError(String expectedErrorMessage, Executable executable) {
        ValidationException exception = assertThrows
                (ValidationException.class, executable);
        assertAll(
                () -> assertEquals(1, exception.getValidationErrors().size()),
                () -> assertEquals(expectedErrorMessage, exception.getValidationErrors().get(0))
        );
    }
}
