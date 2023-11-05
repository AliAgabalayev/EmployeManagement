package com.employee.management.mapper;

import com.employee.management.dao.entity.Employee;
import com.employee.management.model.EmployeeDto;
import com.employee.management.model.EmployeeRequest;
import com.employee.management.model.EmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public abstract class EmployeeMapper {
    public static final EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "id", ignore = true)
    public abstract Employee requestToEntity(EmployeeRequest request);

    public abstract EmployeeDto entityToDto(Employee employee);

    public abstract List<EmployeeDto> entitiesToDtos(List<Employee> employees);

    public abstract EmployeeResponse dtoToResponse(EmployeeDto dto);

    public abstract List<EmployeeResponse> dtoToResponseList(List<EmployeeDto> dtos);
}
