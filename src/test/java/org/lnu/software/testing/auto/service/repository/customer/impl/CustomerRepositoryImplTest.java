package org.lnu.software.testing.auto.service.repository.customer.impl;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType;
import org.junit.jupiter.api.Test;
import org.lnu.software.testing.auto.service.entity.customer.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Sql("/db/schema.sql")
@AutoConfigureEmbeddedDatabase(type = DatabaseType.POSTGRES)
public class CustomerRepositoryImplTest {

    @Autowired
    private CustomerRepositoryImpl customerRepository;

    @Test
    public void create() {
        // Given
        CustomerEntity expectedEntity = new CustomerEntity() {{
            setId(1L);
            setFirstName("Ihor");
            setMiddleName("Vasylovuch");
            setLastName("Petrakov");
            setPhone("+380674562375");
            setEmail("Ihor.Petrakov@gmail.com");
            setInfo("Info about Ihor");
        }};

        CustomerEntity entity = new CustomerEntity() {{
            setFirstName("Ihor");
            setMiddleName("Vasylovuch");
            setLastName("Petrakov");
            setPhone("+380674562375");
            setEmail("Ihor.Petrakov@gmail.com");
            setInfo("Info about Ihor");
        }};

        // When
        CustomerEntity actualEntity = customerRepository.create(entity);

        // Then
        assertEquals(expectedEntity, actualEntity);
    }
}
