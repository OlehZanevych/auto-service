package org.lnu.software.testing.auto.service.controller.handler.exception;

import org.lnu.software.testing.auto.service.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    private static String ERROR = "ERROR";

    record ErrorResponseBody(String status, String message) {
        ErrorResponseBody(String message) {
            this(ERROR, message);
        }
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponseBody> handleBaseException(BaseException e) {
        ResponseStatus responseStatus = e.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus httpStatus = responseStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : responseStatus.value();

        ErrorResponseBody errorResponseBody = new ErrorResponseBody(e.getMessage());
        return new ResponseEntity<>(errorResponseBody, httpStatus);
    }
}
