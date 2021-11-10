package org.lnu.software.testing.auto.service.entity.user;

import lombok.Data;

@Data
public class UserEntity {
    private Long id;
    private String username;
    private boolean isAdmin;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private String info;
    private String passwordHash;
}