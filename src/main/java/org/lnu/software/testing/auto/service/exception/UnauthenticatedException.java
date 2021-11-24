package org.lnu.software.testing.auto.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthenticatedException extends BaseException {
    public UnauthenticatedException(String message) {
        super(message);
    }
}
