package com.company.resourceapi.repositories;

import com.company.resourceapi.entities.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ProjectRepositoryImpl implements CustomSave<Project> {
    private final SdlcSystemRepository repository;

    @Transactional
    @Override
    public Project save(Project entity) {
        entity.setSdlcSystem(repository.findById(1L).orElse(null));
        return entity;
    }
}
