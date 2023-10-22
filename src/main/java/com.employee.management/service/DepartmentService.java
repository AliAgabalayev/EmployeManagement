package com.employee.management.service;

import com.employee.management.dao.entity.Department;
import com.employee.management.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    public Department createDepartment(DepartmentDto departmentDto);

    public Department getDepartmentById(Long id);

    public List<Department> getAllDepartments();

    public Department editDepartament (Long id,DepartmentDto departmentDto);

    public void deleteDepartment(Long id);
}
