package org.lnu.software.testing.auto.service.repository.brand;

import org.lnu.software.testing.auto.service.entity.brand.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "brands")
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}
