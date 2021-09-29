package org.lnu.software.testing.auto.service.service.customer.impl;

import lombok.AllArgsConstructor;
import org.lnu.software.testing.auto.service.dto.customer.BaseCustomerDto;
import org.lnu.software.testing.auto.service.dto.customer.CustomerDto;
import org.lnu.software.testing.auto.service.entity.customer.CustomerEntity;
import org.lnu.software.testing.auto.service.exception.BadRequestException;
import org.lnu.software.testing.auto.service.mapper.CustomerMapper;
import org.lnu.software.testing.auto.service.patch.CustomerPatch;
import org.lnu.software.testing.auto.service.repository.customer.CustomerRepository;
import org.lnu.software.testing.auto.service.service.customer.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto create(BaseCustomerDto customerDto) {
        CustomerEntity customerEntity = customerMapper.toEntity(customerDto);
        customerEntity = customerRepository.create(customerEntity);
        return customerMapper.toDto(customerEntity);
    }

    @Override
    public List<CustomerDto> findAll() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerMapper.toDtoList(customerEntities);
    }

    @Override
    public CustomerDto find(Long id) {
        CustomerEntity customerEntity = customerRepository.find(id);
        return customerMapper.toDto(customerEntity);
    }

    @Override
    public void update(Long id, BaseCustomerDto customerDto) {
        CustomerEntity customerEntity = customerMapper.toEntity(customerDto);
        customerEntity.setId(id);

        customerRepository.update(customerEntity);
    }

    @Override
    public void patch(Long id, CustomerPatch customerPatch) {
        if (customerPatch.isEmpty()) {
            throw new BadRequestException("Customer patch is empty!");
        }

        customerRepository.patch(id, customerPatch);
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);
    }
}
