package org.lnu.software.testing.auto.service.config;

import org.lnu.software.testing.auto.service.entity.brand.BrandEntity;
import org.lnu.software.testing.auto.service.entity.model.ModelEntity;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class RestDataConfig implements RepositoryRestConfigurer {
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(BrandEntity.class, ModelEntity.class);
    }
}
