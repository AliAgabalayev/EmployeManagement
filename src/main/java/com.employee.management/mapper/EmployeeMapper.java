package com.employee.management.mapper;

import com.employee.management.dao.entity.Employee;
import com.employee.management.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public abstract class EmployeeMapper{

    public static final EmployeeMapper INSTANCE= Mappers.getMapper(EmployeeMapper.class);

    public abstract Employee dtoToEntity(EmployeeDto employeeDto);
}
