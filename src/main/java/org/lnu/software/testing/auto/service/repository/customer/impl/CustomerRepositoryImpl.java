package org.lnu.software.testing.auto.service.repository.customer.impl;

import lombok.AllArgsConstructor;
import org.lnu.software.testing.auto.service.entity.customer.CustomerEntity;
import org.lnu.software.testing.auto.service.exception.NotFoundException;
import org.lnu.software.testing.auto.service.repository.customer.CustomerRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private static final String INSERT_CUSTOMER_QUERY = """
            INSERT INTO customers (
                first_name,
                middle_name,
                last_name,
                phone,
                email,
                info
            ) VALUES (
                :firstName,
                :middleName,
                :lastName,
                :phone,
                :email,
                :info
            )
            """;

    private static final String SELECT_CUSTOMER_QUERY = """
            SELECT
                id,
                first_name,
                middle_name,
                last_name,
                phone,
                email,
                info
            FROM customers
            """;

    private static final String SELECT_CUSTOMER_BY_ID_QUERY = """
            SELECT
                id,
                first_name,
                middle_name,
                last_name,
                phone,
                email,
                info
            FROM customers
            WHERE id = :id
            """;

    private static final String UPDATE_CUSTOMER_BY_ID_QUERY = """
            UPDATE customers SET
                first_name = :firstName,
                middle_name = :middleName,
                last_name = :lastName,
                phone = :phone,
                email = :email,
                info = :info
            WHERE id = :id
            """;

    private static final String DELETE_CUSTOMER_BY_ID_QUERY = """
            DELETE FROM customers WHERE id = :id
            """;

    private static final RowMapper<CustomerEntity> CUSTOMER_ROW_MAPPER = (rs, rowNum) -> {
        CustomerEntity entity = new CustomerEntity();

        entity.setId(rs.getObject("id", Long.class));
        entity.setFirstName(rs.getString("first_name"));
        entity.setMiddleName(rs.getString("middle_name"));
        entity.setLastName(rs.getString("last_name"));
        entity.setPhone(rs.getString("phone"));
        entity.setEmail(rs.getString("email"));
        entity.setInfo(rs.getString("info"));

        return entity;
    };

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public CustomerEntity create(CustomerEntity customerEntity) {
        SqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("firstName", customerEntity.getFirstName())
                .addValue("middleName", customerEntity.getMiddleName())
                .addValue("lastName", customerEntity.getLastName())
                .addValue("phone", customerEntity.getPhone())
                .addValue("email", customerEntity.getEmail())
                .addValue("info", customerEntity.getInfo());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(INSERT_CUSTOMER_QUERY, sqlParameters, keyHolder);

        Long customerId = (Long) keyHolder.getKeys().get("id");
        customerEntity.setId(customerId);

        return customerEntity;
    }

    @Override
    public List<CustomerEntity> findAll() {
        return jdbcTemplate.query(SELECT_CUSTOMER_QUERY, CUSTOMER_ROW_MAPPER);
    }

    @Override
    public CustomerEntity find(Long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_BY_ID_QUERY,
                    new MapSqlParameterSource("id", id), CUSTOMER_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Customer with id " + id + " not found!");
        }
    }

    @Override
    public void update(CustomerEntity entity) {
        int affectedRows = jdbcTemplate.update(UPDATE_CUSTOMER_BY_ID_QUERY, new MapSqlParameterSource()
                .addValue("id", entity.getId())
                .addValue("firstName", entity.getFirstName())
                .addValue("middleName", entity.getMiddleName())
                .addValue("lastName", entity.getLastName())
                .addValue("phone", entity.getPhone())
                .addValue("email", entity.getEmail())
                .addValue("info", entity.getInfo()));

        if (affectedRows == 0) {
            throw new NotFoundException("Customer with id " + entity.getId() + " not found!");
        }
    }

    @Override
    public void delete(Long id) {
        int affectedRows = jdbcTemplate.update(DELETE_CUSTOMER_BY_ID_QUERY, new MapSqlParameterSource("id", id));

        if (affectedRows == 0) {
            throw new NotFoundException("Customer with id " + id + " not found!");
        }
    }
}
