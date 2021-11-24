package org.lnu.software.testing.auto.service.service.user.impl;

import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.lnu.software.testing.auto.service.dto.user.UserCreateDto;
import org.lnu.software.testing.auto.service.dto.user.UserDto;
import org.lnu.software.testing.auto.service.entity.user.UserEntity;
import org.lnu.software.testing.auto.service.dto.user.UserUpdateDto;
import org.lnu.software.testing.auto.service.exception.ConflictException;
import org.lnu.software.testing.auto.service.exception.NotFoundException;
import org.lnu.software.testing.auto.service.mapper.UserMapper;
import org.lnu.software.testing.auto.service.repository.user.UserRepository;
import org.lnu.software.testing.auto.service.service.user.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto create(UserCreateDto userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);

        String passwordHash = bCryptPasswordEncoder.encode(userDto.getPassword());
        userEntity.setPasswordHash(passwordHash);

        try {
            userEntity = userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("users_username_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("User with username '%s' already exists!", userEntity.getUsername());
                    throw new ConflictException(errorMessage);
                }
            }

            throw e;
        }
        return userMapper.toDto(userEntity);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userMapper.toDtoList(userEntities);
    }

    @Override
    public UserDto find(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));
        return userMapper.toDto(userEntity);
    }

    @Override
    @Transactional
    public void update(Long id, UserUpdateDto userDto) {
//        UserEntity userEntity = userMapper.toEntity(userDto);
//        try {
//            int affectedRaws = userRepository.update(id, userEntity);
//            if (affectedRaws == 0) {
//                new NotFoundException("User not found!");
//            }
//        } catch (DataIntegrityViolationException e) {
//            Throwable cause = e.getCause();
//            if (cause instanceof ConstraintViolationException) {
//                if ("users_username_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
//                    String errorMessage = String.format("User with username '%s' already exists!", userEntity.getUsername());
//                    throw new ConflictException(errorMessage);
//                }
//            }
//
//            throw e;
//        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = userRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("User not found!");
        }
    }
}

