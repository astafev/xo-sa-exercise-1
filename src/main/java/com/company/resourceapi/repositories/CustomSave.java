package com.company.resourceapi.repositories;

public interface CustomSave<T> {
    <S extends T> S save(S entity);
}
