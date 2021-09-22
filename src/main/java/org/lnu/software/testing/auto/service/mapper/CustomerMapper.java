package org.lnu.software.testing.auto.service.mapper;

import org.lnu.software.testing.auto.service.dto.customer.BaseCustomerDto;
import org.lnu.software.testing.auto.service.dto.customer.CustomerDto;
import org.lnu.software.testing.auto.service.entity.customer.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity toEntity(BaseCustomerDto customerDto);
    CustomerDto toDto(CustomerEntity customerEntity);
    List<CustomerDto> toDtoList(List<CustomerEntity> customerEntities);
}
