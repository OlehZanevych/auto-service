package org.lnu.software.testing.auto.service.repository.brand;

import org.lnu.software.testing.auto.service.entity.brand.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}
