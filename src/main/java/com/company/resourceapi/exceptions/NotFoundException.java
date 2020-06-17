package com.company.resourceapi.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@ToString
public class NotFoundException extends RuntimeException {
    private final Class<?> entityClass;
    private final Serializable id;
}
