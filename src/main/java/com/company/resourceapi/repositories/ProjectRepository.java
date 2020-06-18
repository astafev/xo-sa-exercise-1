package com.company.resourceapi.repositories;

import com.company.resourceapi.entities.Project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "projects")
public interface ProjectRepository extends JpaRepository<Project, Long>, CustomSave<Project> {

    Optional<Project> findBySdlcSystemIdAndId(long sdlcSystemId, long projectId);
}
