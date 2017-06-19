package io.redspark.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends WebException {

    public NotFoundException(String field) {
        super(HttpStatus.NOT_FOUND, field + ".notFound");
    }
}
