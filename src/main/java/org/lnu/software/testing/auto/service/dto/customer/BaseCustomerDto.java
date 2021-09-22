package org.lnu.software.testing.auto.service.dto.customer;

import lombok.Data;

@Data
public class BaseCustomerDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private String info;
}
