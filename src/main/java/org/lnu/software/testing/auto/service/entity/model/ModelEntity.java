package org.lnu.software.testing.auto.service.entity.model;

import lombok.Data;
import org.lnu.software.testing.auto.service.entity.brand.BrandEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "models")
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private BrandEntity brand;

    private Short year;

    private String description;
}
