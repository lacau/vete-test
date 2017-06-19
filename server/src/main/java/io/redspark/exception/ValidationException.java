package io.redspark.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends WebException {

    public ValidationException(String msg) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, msg);
    }
}
