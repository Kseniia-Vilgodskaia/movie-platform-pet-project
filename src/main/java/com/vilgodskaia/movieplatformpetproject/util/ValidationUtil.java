package com.vilgodskaia.movieplatformpetproject.util;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {

    public static final String FIELD_NULL_POSTFIX = " field should not be null";
    public static final String FIELD_EMPTY_POSTFIX = " field should not be empty";

    public static List<String> checkNotNull(String fieldName, Object value) {
        List<String> validationErrors = new ArrayList<>();
        if (value == null) {
            validationErrors.add(fieldName + FIELD_NULL_POSTFIX);
        }
        return validationErrors;
    }

    public static List<String> checkNotEmptyAndNotNull(String fieldName, String value) {
        List<String> validationErrors = checkNotNull(fieldName, value);
        if (value != null && value.isEmpty()) {
            validationErrors.add(fieldName + FIELD_EMPTY_POSTFIX);
        }
        return validationErrors;
    }
}
