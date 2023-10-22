package com.employee.management.mapper;

import com.employee.management.dao.entity.Position;
import com.employee.management.dto.PositionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PositionMapper {
    public static final PositionMapper INSTANCE= Mappers.getMapper(PositionMapper.class);

    public abstract Position dtoToEntity(PositionDto positionDto);
}
