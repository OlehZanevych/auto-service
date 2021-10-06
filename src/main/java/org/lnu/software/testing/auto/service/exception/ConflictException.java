package org.lnu.software.testing.auto.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends BaseException {
    public ConflictException(String message) {
        super(message);
    }
}
