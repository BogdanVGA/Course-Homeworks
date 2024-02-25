package org.curs20.exercise.error;

import jakarta.validation.ValidationException;

public class ValidationError extends ValidationException {

    public ValidationError(String message) {
        super(message);
    }
}
