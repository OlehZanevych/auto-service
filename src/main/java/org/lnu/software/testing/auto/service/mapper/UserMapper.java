package org.lnu.software.testing.auto.service.mapper;

import org.lnu.software.testing.auto.service.dto.user.UserCreateDto;
import org.lnu.software.testing.auto.service.dto.user.UserDto;
import org.lnu.software.testing.auto.service.entity.user.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserCreateDto userDto);

    UserDto toDto(UserEntity userEntity);

    List<UserDto> toDtoList(List<UserEntity> userEntities);
}
