package org.lnu.software.testing.auto.service.service.customer.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lnu.software.testing.auto.service.dto.customer.BaseCustomerDto;
import org.lnu.software.testing.auto.service.dto.customer.CustomerDto;
import org.lnu.software.testing.auto.service.entity.customer.CustomerEntity;
import org.lnu.software.testing.auto.service.mapper.CustomerMapper;
import org.lnu.software.testing.auto.service.repository.customer.CustomerRepository;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void create() {
        // Given
        Long createdCustomerId = 47L;

        CustomerDto expectedCustomerDto = new CustomerDto();
        expectedCustomerDto.setId(createdCustomerId);

        // When
        BaseCustomerDto baseCustomerDto = new BaseCustomerDto();

        CustomerEntity mappedCustomerEntity = new CustomerEntity();

        CustomerEntity createdCustomerEntity = new CustomerEntity();
        createdCustomerEntity.setId(createdCustomerId);

        when(customerMapper.toEntity(baseCustomerDto)).thenReturn(mappedCustomerEntity);
        when(customerRepository.create(mappedCustomerEntity)).thenReturn(createdCustomerEntity);
        when(customerMapper.toDto(createdCustomerEntity)).thenReturn(expectedCustomerDto);

        CustomerDto actualCustomerDto = customerService.create(baseCustomerDto);

        // Then
        assertEquals(expectedCustomerDto, actualCustomerDto);

        InOrder inOrder = inOrder(customerMapper, customerRepository);
        inOrder.verify(customerMapper).toEntity(baseCustomerDto);
        inOrder.verify(customerRepository).create(mappedCustomerEntity);
        inOrder.verify(customerMapper).toDto(createdCustomerEntity);
        inOrder.verifyNoMoreInteractions();
    }
}
