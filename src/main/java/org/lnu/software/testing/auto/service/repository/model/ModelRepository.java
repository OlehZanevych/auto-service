package org.lnu.software.testing.auto.service.repository.model;

import org.lnu.software.testing.auto.service.entity.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    List<ModelEntity> findByYearBetween(Short startYear, Short lastYear);

    @Modifying
    @Query("""
            UPDATE ModelEntity SET 
                name = :#{#entity.name},
                brand.id = :#{#entity.brand.id},
                year = :#{#entity.year},
                description = :#{#entity.description}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") ModelEntity entity);

    @Modifying
    @Query("DELETE FROM ModelEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
