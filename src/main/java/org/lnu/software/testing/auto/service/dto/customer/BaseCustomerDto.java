package org.lnu.software.testing.auto.service.dto.customer;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class BaseCustomerDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String middleName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^(\\+38)?\\d{10}$")
    private String phone;

    @NotBlank
    @Email
    private String email;

    private String info;
}
