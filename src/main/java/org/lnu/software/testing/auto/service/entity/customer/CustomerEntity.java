package org.lnu.software.testing.auto.service.entity.customer;

import lombok.Data;

@Data
public class CustomerEntity {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private String info;
}
