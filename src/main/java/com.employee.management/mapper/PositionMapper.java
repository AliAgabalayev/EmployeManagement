package com.employee.management.mapper;

import com.employee.management.dao.entity.Position;
import com.employee.management.model.PositionDto;
import com.employee.management.model.PositionRequest;
import com.employee.management.model.PositionResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class PositionMapper {
    public static final PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "employees", ignore = true)
    @Mapping(source = "request.departmentId", target = "department.id")
    public abstract Position requestToEntity(PositionRequest request);
    @Mapping(target = "id", expression = "java(position.getId() != null ? position.getId() : 0)")
    public abstract PositionDto entityToDto(Position position);

    public abstract List<PositionDto> entityListToDtoList(List<Position> positions);

    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.salary", target = "salary")
    @Mapping(source = "dto.department.id", target = "departmentId")
    public abstract PositionResponse dtoToResponse(PositionDto dto);

    public abstract List<PositionResponse> dtoToResponseList(List<PositionDto> dtos);
}
