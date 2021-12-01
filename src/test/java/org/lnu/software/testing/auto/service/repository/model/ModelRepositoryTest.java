package org.lnu.software.testing.auto.service.repository.model;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.lnu.software.testing.auto.service.entity.brand.BrandEntity;
import org.lnu.software.testing.auto.service.entity.model.ModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@Sql({"/db/schema.sql", "/db/data.sql"})
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES, refresh = BEFORE_EACH_TEST_METHOD)
public class ModelRepositoryTest {

    @Autowired
    private ModelRepository modelRepository;

    @Test
    public void findAll() {
        // Given
        int expectedModelsCount = 1000;

        // When
        List<ModelEntity> actualModelEntities = modelRepository.findAll();

        // Then
        assertEquals(actualModelEntities.size(), expectedModelsCount);
    }

    @Test
    public void findById() {
        // Given
        Long modelId = 625L;

        Optional<ModelEntity> expectedModelEntityOptional = Optional.of(
                new ModelEntity() {{
                    setId(modelId);
                    setName("Model 625");
                    setBrand(new BrandEntity() {{
                        setId(63L);
                        setName("Brand 63");
                        setDescription("Description 63");
                    }});
                    setYear((short) 2006);
                    setDescription("Model description 625");
                }}
        );

        // When
        Optional<ModelEntity> actualModelEntityOptional = modelRepository.findById(modelId);

        // Then
        assertEquals(actualModelEntityOptional, expectedModelEntityOptional);
    }

    @Test
    @Transactional
    public void update() {
        // Given
        int expectedAffectedRows = 1;

        Long modelId = 576L;

        ModelEntity expectedUpdatedEntity = new ModelEntity() {{
            setId(modelId);
            setName("New model 576");
            setBrand(new BrandEntity() {{
                setId(13L);
                setName("Brand 13");
                setDescription("Description 13");
            }});
            setYear((short) 2004);
            setDescription("New model description 576");
        }};

        ModelEntity modelEntity = new ModelEntity() {{
            setId(modelId);
            setName("New model 576");
            setBrand(new BrandEntity() {{
                setId(13L);
            }});
            setYear((short) 2004);
            setDescription("New model description 576");
        }};

        // When
        int actualAffectedRows = modelRepository.update(modelId, modelEntity);
        ModelEntity actualUpdatedEntity = modelRepository.findById(modelId).get();

        // Then
        assertEquals(actualAffectedRows, expectedAffectedRows);
        assertEquals(actualUpdatedEntity, expectedUpdatedEntity);
    }

    @Test
    @Transactional
    public void removeById() {
        // Given
        int expectedAffectedRows = 1;

        Long modelId = 50L;

        // When
        int actualAffectedRows = modelRepository.removeById(modelId);

        // Then
        assertEquals(actualAffectedRows, expectedAffectedRows);
    }
}
