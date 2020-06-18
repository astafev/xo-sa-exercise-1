package com.company.resourceapi.repositories;

import com.company.resourceapi.entities.SdlcSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
public interface SdlcSystemRepository extends JpaRepository<SdlcSystem, Long>{

}
