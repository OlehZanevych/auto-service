package org.lnu.software.testing.auto.service.dto.user;

import lombok.Data;

@Data
public class UserCreateDto {
    private String username;
    private String password;
    private boolean isAdmin;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private String info;
}
