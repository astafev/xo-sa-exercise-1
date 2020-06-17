package com.company.resourceapi.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@ToString
public class NotFoundException extends RuntimeException {
    private final Class<?> entityClass;
    private final Serializable id;

    public String getMessage() {
        return "Couldn't find " + entityClass.getSimpleName() + " with id " + id;
    }
}
