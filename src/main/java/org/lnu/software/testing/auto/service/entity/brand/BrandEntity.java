package org.lnu.software.testing.auto.service.entity.brand;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "brands")
public class BrandEntity {
    @Id
    private Long id;

    private String name;

    private String description;
}
