package com.company.resourceapi.repositories;

import com.company.resourceapi.entities.Project;

public interface CustomSave<T> {
    <S extends T> S save(S entity);
}
