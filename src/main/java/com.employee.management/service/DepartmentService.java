package com.employee.management.service;

import com.employee.management.model.DepartmentRequest;
import com.employee.management.model.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse getDepartmentById(Long id);

    DepartmentResponse getDepartmentByName(String name);

    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse editDepartment(Long id, DepartmentRequest departmentRequest);

    void deleteDepartment(Long id);
}
