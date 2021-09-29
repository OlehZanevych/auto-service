package org.lnu.software.testing.auto.service.service.customer;

import org.lnu.software.testing.auto.service.dto.customer.BaseCustomerDto;
import org.lnu.software.testing.auto.service.dto.customer.CustomerDto;
import org.lnu.software.testing.auto.service.patch.CustomerPatch;

import java.util.List;

public interface CustomerService {
    CustomerDto create(BaseCustomerDto customerDto);
    List<CustomerDto> findAll();
    CustomerDto find(Long id);
    void update(Long id, BaseCustomerDto customerDto);
    void patch(Long id, CustomerPatch customerPatch);
    void delete(Long id);
}
