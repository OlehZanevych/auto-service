package org.lnu.software.testing.auto.service.mapper;

import org.lnu.software.testing.auto.service.dto.model.BaseModelDto;
import org.lnu.software.testing.auto.service.dto.model.ModelDto;
import org.lnu.software.testing.auto.service.entity.model.ModelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", implementationName = "brandModelMapper")
public interface ModelMapper {
    @Mapping(target = "brand.id", source = "brandId")
    ModelEntity toEntity(BaseModelDto modelDto);

    @Mapping(target = "brandId", source = "brand.id")
    ModelDto toDto(ModelEntity modelEntity);

    List<ModelDto> toDtoList(List<ModelEntity> modelEntities);
}
