package com.company.resourceapi.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@RequiredArgsConstructor
@ToString
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private final Class<?> entityClass;
    private final Serializable id;

    public String getMessage() {
        return "Couldn't find " + entityClass.getSimpleName() + " with id " + id;
    }
}
