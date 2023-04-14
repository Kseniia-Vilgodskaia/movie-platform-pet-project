package com.vilgodskaia.movieplatform.core.config.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final List<String> validationErrors;

    public ValidationException(List<String> validationErrors) {
        super("Validation failed");
        this.validationErrors = validationErrors;
    }
}
