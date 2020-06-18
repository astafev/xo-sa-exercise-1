package com.company.resourceapi.repositories;

import com.company.resourceapi.entities.Project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "projects")
public interface ProjectRepository extends JpaRepository<Project, Long>{

    Optional<Project> findBySdlcSystemIdAndId(long sdlcSystemId, long projectId);
}
