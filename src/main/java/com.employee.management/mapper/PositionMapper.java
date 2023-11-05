package com.employee.management.mapper;

import com.employee.management.dao.entity.Position;
import com.employee.management.model.PositionDto;
import com.employee.management.model.PositionRequest;
import com.employee.management.model.PositionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class PositionMapper {
    public static final PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

    @Mapping(target = "id", ignore = true)
    public abstract Position requestToEntity(PositionRequest request);

    public abstract PositionDto entityToDto(Position position);

    public abstract List<PositionDto> entityListToDtoList(List<Position> positions);

    public abstract PositionResponse dtoToResponse(PositionDto dto);

    public abstract List<PositionResponse> dtoToResponseList(List<PositionDto> dtos);
}
