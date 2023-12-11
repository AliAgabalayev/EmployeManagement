package com.employee.management.mapper;

import com.employee.management.dao.entity.Employee;
import com.employee.management.model.EmployeeDto;
import com.employee.management.model.EmployeeRequest;
import com.employee.management.model.EmployeeResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class EmployeeMapper {
    public static final EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "request.positionId", target = "position.id")
    public abstract Employee requestToEntity(EmployeeRequest request);
    @Mapping(target = "id", expression = "java(employee.getId() != null ? employee.getId() : 0)")
    public abstract EmployeeDto entityToDto(Employee employee);

    public abstract List<EmployeeDto> entitiesToDtos(List<Employee> employees);

    @Mapping(source = "dto.position.id", target = "positionId")
    public abstract EmployeeResponse dtoToResponse(EmployeeDto dto);

    public abstract List<EmployeeResponse> dtoToResponseList(List<EmployeeDto> dtos);
}
