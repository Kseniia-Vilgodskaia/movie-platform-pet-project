package com.example.movieplatformpetproject.api;

import java.util.List;

public class ValidationUtil {

    public static final String FIELD_NULL_POSTFIX = " field should not be null";
    public static final String FIELD_EMPTY_POSTFIX = " field should not be empty";

    public static void checkNotNull(String fieldName, Object value, List<String> validationErrors) {
        if (value == null) {
            validationErrors.add(fieldName + FIELD_NULL_POSTFIX);
        }
    }

    public static void checkNotEmptyAndNotNull(String fieldName, String value, List<String> validationErrors) {
        checkNotNull(fieldName, value, validationErrors);
        if (value != null && value.isEmpty()) {
            validationErrors.add(fieldName + FIELD_EMPTY_POSTFIX);
        }
    }
}
