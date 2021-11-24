package org.lnu.software.testing.auto.service.service.user;

import org.lnu.software.testing.auto.service.dto.user.UserCreateDto;
import org.lnu.software.testing.auto.service.dto.user.UserDto;
import org.lnu.software.testing.auto.service.dto.user.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserDto create(UserCreateDto userDto);
    List<UserDto> findAll();
    UserDto find(Long id);
    void update(Long id, UserUpdateDto userDto);
    void delete(Long id);
}

