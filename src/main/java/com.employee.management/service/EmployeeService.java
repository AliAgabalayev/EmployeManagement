package com.employee.management.service;

import com.employee.management.model.EmployeeDto;
import com.employee.management.model.EmployeeRequest;
import com.employee.management.model.EmployeeResponse;
import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);

    EmployeeResponse getEmployeeById(Long id);

    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse editEmployee(Long id, EmployeeRequest request);

    void deleteEmployee(Long id);
}
