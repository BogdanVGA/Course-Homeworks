package org.curs21.demo.error;

import jakarta.validation.ValidationException;

public class ValidationError extends ValidationException {

    public ValidationError(String message) {
        super(message);
    }
}
