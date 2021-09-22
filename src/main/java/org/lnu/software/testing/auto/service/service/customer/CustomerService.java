package org.lnu.software.testing.auto.service.service.customer;

import org.lnu.software.testing.auto.service.dto.customer.BaseCustomerDto;
import org.lnu.software.testing.auto.service.dto.customer.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto create(BaseCustomerDto customerDto);
    List<CustomerDto> findAll();
    CustomerDto find(Long id);
    void update(Long id, BaseCustomerDto customerDto);
    void delete(Long id);
}
