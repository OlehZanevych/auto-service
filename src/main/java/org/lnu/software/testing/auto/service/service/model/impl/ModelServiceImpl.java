package org.lnu.software.testing.auto.service.service.model.impl;

import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.lnu.software.testing.auto.service.dto.model.BaseModelDto;
import org.lnu.software.testing.auto.service.dto.model.ModelDto;
import org.lnu.software.testing.auto.service.entity.model.ModelEntity;
import org.lnu.software.testing.auto.service.exception.ConflictException;
import org.lnu.software.testing.auto.service.exception.NotFoundException;
import org.lnu.software.testing.auto.service.mapper.ModelMapper;
import org.lnu.software.testing.auto.service.repository.model.ModelRepository;
import org.lnu.software.testing.auto.service.service.model.ModelService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Override
    public ModelDto create(BaseModelDto modelDto) {
        ModelEntity modelEntity = modelMapper.toEntity(modelDto);
        try {
            modelEntity = modelRepository.save(modelEntity);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("models_name_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Model with name '%s' already exists!", modelEntity.getName());
                    throw new ConflictException(errorMessage);
                }
            }
        }
        return modelMapper.toDto(modelEntity);
    }

    @Override
    public List<ModelDto> findAll() {
        List<ModelEntity> modelEntities = modelRepository.findAll();
        return modelMapper.toDtoList(modelEntities);
    }

    @Override
    public ModelDto find(Long id) {
        ModelEntity modelEntity = modelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Model not found!"));
        return modelMapper.toDto(modelEntity);
    }

    @Override
    @Transactional
    public void update(Long id, BaseModelDto modelDto) {
        ModelEntity modelEntity = modelMapper.toEntity(modelDto);
        try {
            int affectedRaws = modelRepository.update(id, modelEntity);
            if (affectedRaws == 0) {
                new NotFoundException("Model not found!");
            }
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("models_name_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Model with name '%s' already exists!", modelEntity.getName());
                    throw new ConflictException(errorMessage);
                }
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = modelRepository.removeById(id);
        if (affectedRaws == 0) {
            new NotFoundException("Model not found!");
        }
    }
}
