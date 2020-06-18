package com.company.resourceapi.repositories;

import com.company.resourceapi.entities.Project;
import com.company.resourceapi.entities.SdlcSystem;
import com.company.resourceapi.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;

@RequiredArgsConstructor
public class ProjectRepositoryImpl implements CustomSave<Project> {
    private final SdlcSystemRepository repository;
    private final EntityManager em;

    @Transactional
    @Override
    public Project save(Project entity) {
        if (entity.getSdlcSystem() != null) {
            Long sdlcId = entity.getSdlcSystem().getId();
            if (sdlcId != null) {
                em.detach(entity.getSdlcSystem());
                SdlcSystem sdlcSystem = repository.findById(sdlcId)
                        .orElseThrow(() -> new NotFoundException(SdlcSystem.class, sdlcId));
                entity.setSdlcSystem(sdlcSystem);
            }
        }
        entity.setLastModifiedDate(Instant.now());
        em.persist(entity);
        return entity;
    }
}
