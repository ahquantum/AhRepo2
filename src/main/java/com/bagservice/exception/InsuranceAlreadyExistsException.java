package com.bagservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when trying to create insurance for a bag that already has insurance
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class InsuranceAlreadyExistsException extends RuntimeException {

    public InsuranceAlreadyExistsException(String message) {
        super(message);
    }

    public InsuranceAlreadyExistsException(String bagTag, boolean isBagTag) {
        super(String.format("Insurance already exists for bag with tag: '%s'", bagTag));
    }
} 