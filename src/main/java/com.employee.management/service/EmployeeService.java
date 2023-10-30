package com.employee.management.service;
import com.employee.management.dao.entity.Employee;
import com.employee.management.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(EmployeeDto employeeDto);

    Employee getEmployeeByEmail(String email);

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployees();

    Employee editEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployee(Long id);
}
