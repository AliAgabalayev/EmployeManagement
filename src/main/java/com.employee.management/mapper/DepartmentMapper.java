package com.employee.management.mapper;

import com.employee.management.dao.entity.Department;
import com.employee.management.model.DepartmentDto;
import com.employee.management.model.DepartmentRequest;
import com.employee.management.model.DepartmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class DepartmentMapper {
    public static final DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(target = "id", ignore = true)
    public abstract Department requestToEntity(DepartmentRequest request);

    public abstract DepartmentResponse entityToResponse(Department department);
    public abstract List<DepartmentResponse> entityListToResponseList(List<Department> departments);
}
