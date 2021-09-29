package org.lnu.software.testing.auto.service.repository.customer;

import org.lnu.software.testing.auto.service.entity.customer.CustomerEntity;
import org.lnu.software.testing.auto.service.patch.CustomerPatch;

import java.util.List;

public interface CustomerRepository {
    CustomerEntity create(CustomerEntity customerEntity);
    List<CustomerEntity> findAll();
    CustomerEntity find(Long id);
    void update(CustomerEntity entity);
    void patch(Long id, CustomerPatch customerPatch);
    void delete(Long id);
}
