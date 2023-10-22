package com.employee.management.mapper;

import com.employee.management.dao.entity.Department;
import com.employee.management.dto.DepartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class DepartmentMapper {

    public static final DepartmentMapper INSTANCE= Mappers.getMapper(DepartmentMapper.class);

    public abstract Department dtoToEntity(DepartmentDto departmentDto);
}
