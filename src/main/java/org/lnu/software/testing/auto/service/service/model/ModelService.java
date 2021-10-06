package org.lnu.software.testing.auto.service.service.model;

import org.lnu.software.testing.auto.service.dto.model.BaseModelDto;
import org.lnu.software.testing.auto.service.dto.model.ModelDto;

import java.util.List;

public interface ModelService {
    ModelDto create(BaseModelDto modelDto);
    List<ModelDto> findAll();
    ModelDto find(Long id);
    void update(Long id, BaseModelDto modelDto);
    void delete(Long id);
}
