package org.lnu.software.testing.auto.service.repository.model;

import org.lnu.software.testing.auto.service.entity.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "models")
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    List<ModelEntity> findByYearBetween(Short startYear, Short lastYear);
}
